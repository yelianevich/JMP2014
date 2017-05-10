package com.epam.jmp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.epam.jmp.classloading.module.impl.ClassLoadingTests;
import com.epam.jmp.concurrency.impl.ConcurrencyTests;
import com.epam.jmp.memory.cache.service.MemoryManagementTests;
import com.epam.jmp.util.UtilTests;

@RunWith(Suite.class)
@SuiteClasses({
        ClassLoadingTests.class,
        ConcurrencyTests.class,
        MemoryManagementTests.class,
        UtilTests.class })
public class JmpSuite {

}
