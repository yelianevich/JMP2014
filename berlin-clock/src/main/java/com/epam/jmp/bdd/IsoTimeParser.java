package com.epam.jmp.bdd;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class IsoTimeParser implements TimeParser {

	@Override
	public LocalTime parse(String time) {
		return LocalTime.parse(time, DateTimeFormatter.ISO_TIME);
	}

}
