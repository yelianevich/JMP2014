package com.epam.jmp

import org.gradle.api.Project
import org.gradle.api.tasks.testing.TestListener
import org.gradle.api.tasks.testing.TestDescriptor
import org.gradle.api.tasks.testing.TestResult

class NotificationTestListener implements TestListener {
    final Project project

    NotificationTestListener(Project project) {
        this.project = project
    }

    @Override
    void afterSuite(TestDescriptor suite, TestResult result) {
        if (!suite.parent && result.getTestCount() > 0) {
            long elapsedTestTime = result.getEndTime() - result.getStartTime()
            project.logger.lifecycle("Elapsed time for execution of ${result.getTestCount()} test(s): $elapsedTestTime ms", 'local')
        }
    }

    @Override
    void afterTest(TestDescriptor testDescriptor, TestResult result) {}

    @Override
    void beforeSuite(TestDescriptor suite) {}

    @Override
    void beforeTest(TestDescriptor testDescriptor) {}
}
