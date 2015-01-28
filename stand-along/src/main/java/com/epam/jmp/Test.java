package com.epam.jmp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test {

	public static void main(String[] args) {
		String dateString = "2015-01-18T21:00:00.000Z";
		LocalDateTime date = LocalDateTime.parse(dateString, DateTimeFormatter.ISO_DATE_TIME);

		dateString = date.format(DateTimeFormatter.ISO_DATE_TIME);
		System.out.println(dateString + ".000Z");
	}

}
