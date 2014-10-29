package com.epam.jmp.concurrency.job;

import com.epam.jmp.concurrency.files.FolderReader;
import com.epam.jmp.util.Config;

import static com.epam.jmp.util.Config.INPUT_FOLDER;;

public class ImportJob implements Runnable {

	@Override
	public void run() {
		String dir = Config.INST.prop(INPUT_FOLDER);
		FolderReader reader = new FolderReader(dir);
		ReaderJob readerJob = new ReaderJob(reader);
		readerJob.run();

	}

}
