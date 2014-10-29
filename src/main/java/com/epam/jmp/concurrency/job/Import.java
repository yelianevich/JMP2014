package com.epam.jmp.concurrency.job;

import static com.epam.jmp.util.Config.INPUT_FOLDER;

import com.epam.jmp.concurrency.files.FolderReader;
import com.epam.jmp.concurrency.service.LocalNewsService;
import com.epam.jmp.concurrency.service.NewsService;
import com.epam.jmp.util.Config;

public class Import implements Runnable {

	@Override
	public void run() {
		String dir = Config.INST.prop(INPUT_FOLDER);
		FolderReader reader = new FolderReader(dir);
		NewsService newsService = new LocalNewsService();
		ImportJob readerJob = new ImportJob(reader, newsService);
		readerJob.run();
	}

}
