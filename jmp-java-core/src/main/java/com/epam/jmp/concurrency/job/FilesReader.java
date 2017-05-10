package com.epam.jmp.concurrency.job;

import java.nio.file.Path;
import java.util.List;

public interface FilesReader {

    List<Path> readFiles();

}
