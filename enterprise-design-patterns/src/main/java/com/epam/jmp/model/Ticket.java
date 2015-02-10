package com.epam.jmp.model;

import com.epam.jmp.transaction.CloneableData;


public class Ticket implements CloneableData<Ticket> {
	private int price;
	private int seat;
	private int row;

	@Override
	public Ticket cloneData() {
		// TODO Auto-generated method stub
		return null;
	}
}
