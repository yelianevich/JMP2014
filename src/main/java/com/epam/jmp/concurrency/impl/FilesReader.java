package com.epam.jmp.concurrency.impl;

import java.nio.file.Path;
import java.util.List;

public interface FilesReader {

	List<Path> readFiles();

}
