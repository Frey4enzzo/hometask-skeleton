package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;

import java.util.Set;

public interface TicketDao {

    void buy(Ticket ticket);

    void buy(Set<Ticket> tickets);

    Set<Ticket> getSoldTicketsForEvent(Event event);
}
