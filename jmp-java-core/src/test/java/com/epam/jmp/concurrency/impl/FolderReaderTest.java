package com.epam.jmp.concurrency.impl;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.epam.jmp.concurrency.job.FolderReader;

public class FolderReaderTest {
	private static final Logger LOG = LogManager.getLogger(FolderReaderTest.class);
	private FolderReader folderReader;
	private Path dir;
	private Path file1;
	private Path file2;
	private Path subDir;
	private Path subDirFile;

	private List<Path> files;

	@Before
	public void setUp() throws IOException {
		String dirPath = "directory";
		String subDirPath = "subdirectory";
		folderReader = new FolderReader(dirPath);
		dir = Files.createDirectory(Paths.get(dirPath));
		file1 = dir.resolve(Paths.get("file1.ext")).toAbsolutePath();
		file2 = dir.resolve(Paths.get("file2.ext")).toAbsolutePath();
		Files.createFile(file1);
		Files.createFile(file2);

		subDir = Files.createDirectory(dir.resolve(Paths.get(subDirPath)));
		subDirFile = subDir.resolve(Paths.get("subdirfile")).toAbsolutePath();
		Files.createFile(subDirFile);

		files = new ArrayList<>();
		files.add(file1);
		files.add(file2);
		files.add(subDirFile);
	}

	@Test
	public void shouldReturnPathsFromFolderAndSubfolders() {
		List<Path> filesRead = folderReader.readFiles();
		assertEquals(filesRead, files);
	}

	@After
	public void tearDown() throws IOException {
		files.forEach(path -> {
			try {
				Files.delete(path);
			} catch (IOException e) {
				LOG.error("Cannot delete file " + path.getFileName());
			}
		});
		Files.deleteIfExists(subDir);
		Files.deleteIfExists(dir);
	}
}
