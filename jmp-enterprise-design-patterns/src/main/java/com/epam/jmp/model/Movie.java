package com.epam.jmp.model;

import java.util.List;

import com.epam.jmp.transaction.CloneableData;

public class Movie implements CloneableData<Movie> {
    private String name;
    private List<Ticket> tickets;

    @Override
    public Movie cloneData() {
        // TODO Auto-generated method stub
        return null;
    }

}
