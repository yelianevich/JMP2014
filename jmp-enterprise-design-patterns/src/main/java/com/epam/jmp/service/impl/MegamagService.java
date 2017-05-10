package com.epam.jmp.service.impl;

import java.util.List;

import com.epam.jmp.dao.TicketDao;
import com.epam.jmp.model.Cinema;
import com.epam.jmp.service.ExternalTicketService;
import com.epam.jmp.transaction.TransactionParticipant;

public class MegamagService implements ExternalTicketService, TransactionParticipant {

    private TicketDao ticketDao;

    @Override
    public boolean processPurchase(Cinema cinema) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Cinema> getTicketInfo() {
        // TODO Auto-generated method stub
        return null;
    }

    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

}
