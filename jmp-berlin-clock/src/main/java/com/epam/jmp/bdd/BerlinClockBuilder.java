package com.epam.jmp.bdd;

public interface BerlinClockBuilder<T> {

    T clock();

    BerlinClockBuilder<T> second(int seconds);

    BerlinClockBuilder<T> hour(int hours);

    BerlinClockBuilder<T> minute(int mins);

}
