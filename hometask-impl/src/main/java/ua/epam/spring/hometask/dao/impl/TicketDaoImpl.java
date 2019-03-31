package ua.epam.spring.hometask.dao.impl;

import ua.epam.spring.hometask.dao.TicketDao;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TicketDaoImpl implements TicketDao {

    private static Map<Long, Ticket> onSaleTickets = new HashMap<>();

    private static Set<Ticket> soldTickets = new HashSet<>();

    @Override
    public void buy(Ticket ticket) {
        onSaleTickets.values().removeIf(t -> t.equals(ticket));
        soldTickets.add(ticket);
    }

    @Override
    public void buy(Set<Ticket> tickets) {
        onSaleTickets.values().removeAll(tickets);
        soldTickets.addAll(tickets);
    }

    @Override
    public Set<Ticket> getSoldTicketsForEvent(Event event) {
        return soldTickets.stream().filter(ticket -> ticket.getEvent().equals(event)).collect(Collectors.toSet());
    }

    public Set<Ticket> getOnSaleTickets(Event event, LocalDateTime dateTime) {
        return onSaleTickets.values().stream()
                .filter(ticket -> (ticket.getEvent().equals(event) && ticket.getDateTime().equals(dateTime)))
                .collect(Collectors.toSet());
    }
}
