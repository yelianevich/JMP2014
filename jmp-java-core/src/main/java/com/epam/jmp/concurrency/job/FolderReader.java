package com.epam.jmp.concurrency.job;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FolderReader implements FilesReader {
	private static final Logger LOG = LogManager.getLogger();
	private String folderPath;

	public FolderReader(String folderPath) {
		this.folderPath = folderPath;
	}

	/**
	 * @return list with absolute paths to files in folder
	 */
	@Override
	public List<Path> readFiles() {
		Path repoPath = Paths.get(folderPath);
		GetFilesVisitor filesVisitor = new GetFilesVisitor();

		try {
			Files.walkFileTree(repoPath, filesVisitor);
		} catch (IOException e) {
			LOG.error("Error during inout folder reading", e);
		}
		return filesVisitor.getFiles();
	}

	private static class GetFilesVisitor extends SimpleFileVisitor<Path> {
		private final List<Path> files = new ArrayList<>();

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			files.add(file.toAbsolutePath());
			return FileVisitResult.CONTINUE;
		}

		public List<Path> getFiles() {
			return files;
		}
	}
}
