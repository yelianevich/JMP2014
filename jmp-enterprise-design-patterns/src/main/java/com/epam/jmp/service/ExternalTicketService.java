package com.epam.jmp.service;

import java.util.List;

import com.epam.jmp.model.Cinema;

public interface ExternalTicketService {

	List<Cinema> getTicketInfo();

	boolean processPurchase(Cinema cinema);

}
