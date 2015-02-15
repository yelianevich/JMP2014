package com.epam.jmp.bdd.integration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.epam.jmp.bdd.BerlinClockTimeConvertor;
import com.epam.jmp.bdd.TimeConverter;

/**
 * Acceptance test class that uses the JBehave (Gerkin) syntax for writing stories.
 */
public class BerlinClockFixture {

	private TimeConverter berlinClock;
	private String theTime;

	@Given("time converter")
	public void converterAvailable() {
		berlinClock = new BerlinClockTimeConvertor();
	}
	
	@When("the time is $time")
	public void whenTheTimeIs(String time) {
		theTime = time;
	}

	@Then("the clock should look like $")
	public void thenTheClockShouldLookLike(String theExpectedBerlinClockOutput) {
		assertThat(berlinClock.convertTime(theTime), is(equalTo(theExpectedBerlinClockOutput)));
	}
}
