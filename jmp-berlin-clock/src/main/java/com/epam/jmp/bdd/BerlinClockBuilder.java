package com.epam.jmp.bdd;

public interface BerlinClockBuilder<T> {

	T clock();

	BerlinClockBuilder<T> second(int seconds);

	BerlinClockBuilder<String> hour(int hours);

	BerlinClockBuilder<String> minute(int mins);

}
