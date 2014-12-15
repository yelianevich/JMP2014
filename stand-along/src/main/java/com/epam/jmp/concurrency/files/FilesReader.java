package com.epam.jmp.concurrency.files;

import java.nio.file.Path;
import java.util.List;

public interface FilesReader {

	List<Path> readFiles();

}
