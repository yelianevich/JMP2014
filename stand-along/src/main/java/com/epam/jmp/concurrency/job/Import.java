package com.epam.jmp.concurrency.job;

import static com.epam.jmp.util.Config.INPUT_FOLDER;

import com.epam.jmp.concurrency.db.Jdbc;
import com.epam.jmp.concurrency.files.FolderReader;
import com.epam.jmp.concurrency.model.dao.HsqldbNewsDao;
import com.epam.jmp.concurrency.model.dao.NewsMapper;
import com.epam.jmp.concurrency.model.service.LocalNewsService;
import com.epam.jmp.concurrency.model.service.NewsService;
import com.epam.jmp.util.Config;

public class Import implements Runnable {

	@Override
	public void run() {
		String dir = Config.INST.prop(INPUT_FOLDER);
		FolderReader reader = new FolderReader(dir);
		NewsService newsService = new LocalNewsService();
		HsqldbNewsDao newsDao = new HsqldbNewsDao();
		newsDao.setJdbc(Jdbc.INST.getTemplate());
		newsDao.setNewsMapper(new NewsMapper());
		newsService.setNewsDao(newsDao);
		ImportJob readerJob = new ImportJob(reader, newsService);
		readerJob.run();
	}

}
