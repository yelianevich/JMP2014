package com.epam.jmp.bdd;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class BerlinClockBuilderTest {
    private static final String OFF_CLOCK = "O\r\nOOOO\r\nOOOO\r\nOOOOOOOOOOO\r\nOOOO";
    private static final String CLOCK_2_SECONDS = "Y\r\nOOOO\r\nOOOO\r\nOOOOOOOOOOO\r\nOOOO";
    private static final String CLOCK_6_HOUR = "O\r\nROOO\r\nROOO\r\nOOOOOOOOOOO\r\nOOOO";
    private static final String CLOCK_26_MINUTES = "O\r\nOOOO\r\nOOOO\r\nYYRYYOOOOOO\r\nYOOO";
    private static final String CLOCK_6H_26M_2S = "Y\r\nROOO\r\nROOO\r\nYYRYYOOOOOO\r\nYOOO";
    private BerlinClockBuilder<String> clockBuilder;

    @Before
    public void setUp() throws Exception {
        clockBuilder = new TextBerlinClockBuilder();
    }

    @Test
    public void shouldBuildTurnedOffClock() {
        String offClock = clockBuilder.clock();
        assertThat(offClock, is(equalTo(OFF_CLOCK)));
    }

    @Test
    public void shouldHaveSecondLampOnEveryTwoSeconds() {
        String secondsClock = clockBuilder.second(2).clock();
        assertThat(secondsClock, is(equalTo(CLOCK_2_SECONDS)));
    }

    @Test
    public void shouldHave1FiveHourPlus1HourLampsOnAt6() {
        String fiveHourClock = clockBuilder.hour(6).clock();
        assertThat(fiveHourClock, is(equalTo(CLOCK_6_HOUR)));
    }

    @Test
    public void shouldHave5FiveAnd1OneMinuteLampsAt26() {
        String minutesClock = clockBuilder.minute(26).clock();
        assertThat(minutesClock, is(equalTo(CLOCK_26_MINUTES)));
    }

    @Test
    public void shouldShow6Hour26Minutes2Seconds() throws Exception {
        String fullClock = clockBuilder.hour(6).minute(26).second(2).clock();
        assertThat(fullClock, is(equalTo(CLOCK_6H_26M_2S)));
    }

}
