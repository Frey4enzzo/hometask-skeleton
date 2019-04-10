package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.dao.TicketDao;
import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.BookingService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import static ua.epam.spring.hometask.util.SmartUtils.isEmpty;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private TicketDao ticketDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user, @Nonnull Set<Long> seats) {
        return 0;
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {
//        for (Ticket ticket : tickets) {
//            if (!isEmpty(ticket.getUser())) {
//                User user = userDao.getUserByEmail(ticket.getUser().getEmail());
//                if (user != null) user.getTickets().add(ticket);
//            }
//        }
//        ticketDao.buy(tickets);
    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        return ticketDao.getSoldTicketsForEvent(event).stream().filter
                (ticket -> ticket.getDateTime().equals(dateTime)).collect(Collectors.toSet());
    }
}
