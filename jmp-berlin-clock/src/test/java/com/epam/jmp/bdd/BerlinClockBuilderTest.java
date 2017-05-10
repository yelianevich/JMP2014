package com.epam.jmp.bdd;

import org.junit.Before;
import org.junit.Test;

import static com.epam.jmp.bdd.TextBerlinClockBuilder.NEW_ROW;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class BerlinClockBuilderTest {
    private static final String OFF_CLOCK = String.format("O%1$sOOOO%1$sOOOO%1$sOOOOOOOOOOO%1$sOOOO", NEW_ROW);
    private static final String CLOCK_2_SECONDS = String.format("Y%1$sOOOO%1$sOOOO%1$sOOOOOOOOOOO%1$sOOOO", NEW_ROW);
    private static final String CLOCK_6_HOUR = String.format("O%1$sROOO%1$sROOO%1$sOOOOOOOOOOO%1$sOOOO", NEW_ROW);
    private static final String CLOCK_26_MINUTES = String.format("O%1$sOOOO%1$sOOOO%1$sYYRYYOOOOOO%1$sYOOO", NEW_ROW);
    private static final String CLOCK_6H_26M_2S = String.format("Y%1$sROOO%1$sROOO%1$sYYRYYOOOOOO%1$sYOOO", NEW_ROW);
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
