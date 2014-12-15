package com.epam.jmp.classloading.module.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ MultiplyTest.class, SumTest.class })
public class ClassLoadingTests {

}
