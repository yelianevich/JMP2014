package com.epam.jmp.dao.impl;

import com.epam.jmp.dao.TicketDao;
import com.epam.jmp.model.Cinema;
import com.epam.jmp.model.TicketStore;
import com.epam.jmp.transaction.StateSaver;

public class MegamagInMemoryDao implements TicketDao {

    private TicketStore ticketData;
    private StateSaver<Cinema> state;

    @Override
    public TicketStore getTicketInfo() {
        return ticketData;
    }

    @Override
    public boolean processPurchase(Cinema cinema) {
        // TODO Auto-generated method stub
        return false;
    }

}
