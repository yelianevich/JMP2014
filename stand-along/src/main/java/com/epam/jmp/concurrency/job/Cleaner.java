package com.epam.jmp.concurrency.job;

import static java.nio.file.StandardCopyOption.ATOMIC_MOVE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Cleaner {
	private static final Logger LOG = LogManager.getLogger(Cleaner.class);

	/**
	 * @return true if xml file was moved to error folder
	 */
	public static boolean handleImportError(IOException e, Path file, Path errorDir) {
		boolean moved = false;
		LOG.error(file + " is not valid. Move to error folder.", e);
		try {
			Files.move(file, errorDir.resolve(file.getFileName()), ATOMIC_MOVE);
			moved = true;
		} catch (IOException e1) {
			LOG.error("Cannot move file " + file, e);
			try {
				Files.deleteIfExists(file);
			} catch (IOException e2) {
				LOG.error("Cannot delete file " + file, e);
			}
		}
		return moved;
	}
	
	private Cleaner() {}
}
