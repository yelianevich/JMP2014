package com.epam.jmp.concurrency.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ FolderReaderTest.class, NewsImportProcessorTest.class })
public class ConcurrencyTests {

}
