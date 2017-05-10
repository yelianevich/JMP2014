package com.epam.jmp.concurrency;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epam.jmp.concurrency.config.ConcurrencyConfig;
import com.epam.jmp.concurrency.job.ImportJob;

public final class ConcurrencyDemo {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConcurrencyConfig.class);
        ImportJob importJob = context.getBean(ImportJob.class);

        importJob.run();

        // Emulate, that application is running
        Thread.sleep(40 * 1000);
    }

}
