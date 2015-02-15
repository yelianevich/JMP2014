package com.epam.jmp.bdd;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class BerlinClockTimeConvertor implements TimeConverter {

	private static final char RED = 'R';
	private static final char YELLOW = 'Y';
	private static final char OFF = 'O';
	private static final String NEW_ROW = System.lineSeparator();

	@Override
	public String convertTime(String time) {
		LocalTime now = LocalTime.parse(time, DateTimeFormatter.ISO_TIME);
		int seconds = now.getSecond();
		int twoSecondLamp = seconds % 2 == 0 ? 1 : 0;
		int hour = now.getHour();
		int redLampsFiveHours = hour / 5;
		int redLampsOneHour = hour % 5;
		int mins = now.getMinute();
		int yellowRedFiveMins = mins / 5;
		int yellowLampsOneMinute = mins % 5;

		StringBuilder berlinClockPicture = new StringBuilder()
				.append(createLampsRow(OFF, YELLOW, 1, twoSecondLamp))
				.append(NEW_ROW)
				.append(createLampsRow(OFF, RED, 4, redLampsFiveHours))
				.append(NEW_ROW)
				.append(createLampsRow(OFF, RED, 4, redLampsOneHour))
				.append(NEW_ROW)
				.append(createTwoColorLampsRow(OFF, YELLOW, RED, 11, yellowRedFiveMins, 3))
				.append(NEW_ROW)
				.append(createLampsRow(OFF, YELLOW, 4, yellowLampsOneMinute));

		return berlinClockPicture.toString();
	}

	private char[] createLampsRow(char off, char on, int lampsCount, int onLampsCount) {
		char[] lamps = new char[lampsCount];
		Arrays.fill(lamps, off);
		for(int i = 0; i < onLampsCount; ++i) {
			lamps[i] = on;
		}
		return lamps;
	}

	private char[] createTwoColorLampsRow(char off, char on, char specialColor, int lampsCount, int onLampsCount, int everyPosition) {
		char[] lamps = createLampsRow(off, on, lampsCount, onLampsCount);
		return paintSpecialLamps(lamps, everyPosition, on, specialColor);
	}
	
	private char[] paintSpecialLamps(char[] lamps, int everyPosition, char on, char specialColor) {
		int lampsCount = lamps.length;
		for (int i = 0; i < lampsCount; ++i) {
			boolean isLampToPaint = (i + 1) % everyPosition == 0;
			boolean lampIsOn = lamps[i] == on;
			if (isLampToPaint && lampIsOn) {
				lamps[i] = specialColor;
			}
		}
		return lamps;
	}
}