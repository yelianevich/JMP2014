package com.epam.jmp;

import java.util.concurrent.ConcurrentHashMap;

public final class CinemaTicketApp {

    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> ticketPrice = new ConcurrentHashMap<>();
        System.out.println(ticketPrice.putIfAbsent("Людзі на балоце", 300000)); // null
    }

}
