package com.epam.jmp.dao;

import com.epam.jmp.model.Cinema;
import com.epam.jmp.model.TicketStore;

public interface TicketDao {

	TicketStore getTicketInfo();

	boolean processPurchase(Cinema cinema);

}
