package com.epam.jmp.memory.cache.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ImageSoftDecoratorTest.class, ImageWeakCacheDecoratorTest.class,
		WebImageProviderTest.class })
public class MemoryManagementTests {

}
