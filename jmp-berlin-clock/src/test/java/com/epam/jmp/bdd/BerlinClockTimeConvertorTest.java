package com.epam.jmp.bdd;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BerlinClockTimeConvertorTest {
    private static final int SECOND = 11;
    private static final int MINUTE = 35;
    private static final int HOUR = 5;
    private static final String SAMPLE_TIME = HOUR + ":" + MINUTE + ":" + SECOND;
    private static final String SAMPLE_CLOCK = "O\r\nROOO\r\nOOOO\r\nYYRYYRYOOOOO\r\nYOOO";
    private TimeConverter timeConverter;
    @Mock private TimeParser timeParser;
    @Mock private BerlinClockBuilder<String> clockBuilder;

    @Before
    public void setUp() throws Exception {
        given(timeParser.parse(SAMPLE_TIME)).willReturn(LocalTime.of(HOUR, MINUTE, SECOND));

        given(clockBuilder.hour(HOUR)).willReturn(clockBuilder);
        given(clockBuilder.minute(MINUTE)).willReturn(clockBuilder);
        given(clockBuilder.second(SECOND)).willReturn(clockBuilder);
        given(clockBuilder.clock()).willReturn(SAMPLE_CLOCK);

        BerlinClockTimeConvertor convertor = new BerlinClockTimeConvertor();
        convertor.setClockBuilder(clockBuilder);
        convertor.setTimeParser(timeParser);
        timeConverter = convertor;
    }

    @Test
    public void shouldConvert5H35M11S() {
        String berlinClock = timeConverter.convertTime(SAMPLE_TIME);
        assertThat(berlinClock, is(equalTo(SAMPLE_CLOCK)));
        verify(timeParser).parse(SAMPLE_TIME);
        verify(clockBuilder).hour(HOUR);
        verify(clockBuilder).minute(MINUTE);
        verify(clockBuilder).second(SECOND);
        verify(clockBuilder).clock();
    }

}
