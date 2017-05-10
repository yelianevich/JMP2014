package com.epam.jmp.bdd;

import java.util.Arrays;

public class TextBerlinClockBuilder implements BerlinClockBuilder<String> {
    public static final String NEW_ROW = System.lineSeparator();

    private static final char RED = 'R';
    private static final char YELLOW = 'Y';
    private static final char OFF = 'O';

    private char[] secondsLamp = new char[1];
    private char[] fiveHoursRedLamps = new char[4];
    private char[] oneHourRedLamps = new char[4];
    private char[] fiveMinutesYellowRedLamps = new char[11];
    private char[] oneMinuteYellowLamps = new char[4];

    public TextBerlinClockBuilder() {
        turnClockOff();
    }

    private void turnClockOff() {
        Arrays.fill(secondsLamp, OFF);
        Arrays.fill(fiveHoursRedLamps, OFF);
        Arrays.fill(oneHourRedLamps, OFF);
        Arrays.fill(fiveMinutesYellowRedLamps, OFF);
        Arrays.fill(oneMinuteYellowLamps, OFF);
    }

    @Override
    public BerlinClockBuilder<String> hour(int hours) {
        int fiveHoursCount = hours / 5;
        int oneHourCount = hours % 5;
        turnOnLampsRow(fiveHoursRedLamps, RED, fiveHoursCount);
        turnOnLampsRow(oneHourRedLamps, RED, oneHourCount);
        return this;
    }

    private void turnOnLampsRow(char[] lamps, char on, int onLampsCount) {
        for(int i = 0; i < onLampsCount; ++i) {
            lamps[i] = on;
        }
    }

    @Override
    public BerlinClockBuilder<String> minute(int mins) {
        int fiveMinsCount = mins / 5;
        int oneMinCount = mins % 5;
        turnOnLampsRow(fiveMinutesYellowRedLamps, YELLOW, fiveMinsCount);
        paintQuoterLamps(fiveMinutesYellowRedLamps, YELLOW, RED);
        turnOnLampsRow(oneMinuteYellowLamps, YELLOW, oneMinCount);
        return this;
    }

    private char[] paintQuoterLamps(char[] lamps, char on, char specialColor) {
        int lampsCount = lamps.length;
        for (int i = 0; i < lampsCount; ++i) {
            boolean isQuoterLamp = (i + 1) % 3 == 0;
            boolean lampIsOn = lamps[i] == on;
            if (isQuoterLamp && lampIsOn) {
                lamps[i] = specialColor;
            }
        }
        return lamps;
    }

    @Override
    public BerlinClockBuilder<String> second(int second) {
        boolean isSecondTick = second % 2 == 0;
        secondsLamp[0] = isSecondTick ? YELLOW : OFF;
        return this;
    }

    @Override
    public String clock() {
        return String.valueOf(secondsLamp) + NEW_ROW +
                String.valueOf(fiveHoursRedLamps) + NEW_ROW +
                String.valueOf(oneHourRedLamps) + NEW_ROW +
                String.valueOf(fiveMinutesYellowRedLamps) + NEW_ROW +
                String.valueOf(oneMinuteYellowLamps);
    }

}
