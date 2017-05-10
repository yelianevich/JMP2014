package com.epam.jmp.bdd;

import java.time.LocalTime;

public class BerlinClockTimeConvertor implements TimeConverter {
    private TimeParser timeParser;
    private BerlinClockBuilder<String> clockBuilder;

    @Override
    public String convertTime(String time) {
        LocalTime now = timeParser.parse(time);
        String textBerlinClock = clockBuilder
                .hour(now.getHour())
                .minute(now.getMinute())
                .second(now.getSecond())
                .clock();
        return textBerlinClock;
    }

    public void setTimeParser(TimeParser timeParser) {
        this.timeParser = timeParser;
    }

    public void setClockBuilder(BerlinClockBuilder<String> clockBuilder) {
        this.clockBuilder = clockBuilder;
    }
}