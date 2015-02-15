package com.epam.jmp.bdd.integration;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.ParameterConverters.DateConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BerlinClock extends JUnitStory {
	private static final Logger LOG = LoggerFactory.getLogger(BerlinClock.class);

	@Override
	public InjectableStepsFactory stepsFactory() {
		return new InstanceStepsFactory(configuration(), new BerlinClockFixture());
	}

	@Override
	public Configuration configuration() {
		return new MostUsefulConfiguration()
				.useStoryLoader(new LoadFromClasspath(this.getClass()))
				.useParameterConverters(new ParameterConverters().addConverters(new DateConverter()))
				.useStoryReporterBuilder(
						new StoryReporterBuilder()
							.withDefaultFormats()
							.withFormats(Format.CONSOLE, Format.HTML)
							.withFailureTrace(true));
	}

}
