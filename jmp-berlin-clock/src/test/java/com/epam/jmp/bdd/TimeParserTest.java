package com.epam.jmp.bdd;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

public class TimeParserTest {
	private TimeParser timeParser;

	@Before
	public void setUp() throws Exception {
		timeParser = new IsoTimeParser();
	}

	@Test(expected = RuntimeException.class)
	public void shouldThrowExceptionOnIllegalHours() {
		timeParser.parse("24:00:00");
	}
	
	public void shouldThrowExceptionOnIllegalMinutes() {
		LocalTime parsedTime = timeParser.parse("05:50:20");
		LocalTime time = LocalTime.of(5, 50, 20);
		assertThat(parsedTime, is(equalTo(time)));
	}

}
