package com.epam.jmp.concurrency.job;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.jmp.concurrency.model.News;
import com.epam.jmp.concurrency.service.LocalNewsService;
import com.epam.jmp.concurrency.service.NewsService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class NewsImportProcessor implements Callable<Boolean> {
	private Logger LOG = LogManager.getLogger(LocalNewsService.class);
	private Path file;
	private NewsService newsService;
	private Path errorDir;
	private boolean imported;
	private boolean moved;

	public NewsImportProcessor(Path file, Path errorDir, NewsService newsService) {
		this.file = file;
		this.newsService = newsService;
		this.errorDir = errorDir;
	}

	@Override
	public Boolean call() {
		imported = false;
		moved = false;
		ObjectMapper mapper = new XmlMapper();
		try {
			List<String> lines = Files.readAllLines(file);
			StringBuilder builder = new StringBuilder();
			lines.forEach(str -> builder.append(str));
			News news = mapper.readValue(builder.toString(), News.class);
			newsService.upsertNews(news);
			Files.delete(file);
			imported = true;
		} catch (JsonMappingException | JsonParseException e) {
			moved = Cleaner.handleImportError(e, file, errorDir);
		}
		catch (IOException e) {
			LOG.error("Import failed", e);
			throw new RuntimeException(e);
		}
		return imported;
	}

	public boolean isMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	public boolean isImported() {
		return imported;
	}

	public void setImported(boolean imported) {
		this.imported = imported;
	}

}
