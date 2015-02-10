package com.epam.jmp.model;

import java.util.List;

import com.epam.jmp.transaction.CloneableData;

public class Cinema implements CloneableData<Cinema> {
	private String name;
	private List<Movie> movies;

	@Override
	public Cinema cloneData() {
		// TODO Auto-generated method stub
		return null;
	}

}
