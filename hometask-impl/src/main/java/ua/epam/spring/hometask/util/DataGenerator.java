package ua.epam.spring.hometask.util;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import java.time.LocalDateTime;
import java.util.*;

import static ua.epam.spring.hometask.util.Constants.DateTimeConstants.*;

public class DataGenerator {

//    public static Map<Long, User> createUsers() {
//        Map<Long, User> users = new HashMap<>();
//        for (int i = 0; i < 5; i++) {
//            users.put(Long.valueOf(i), new User("User FirstName " + i+1, "User LastName " + i+1, "User Email " + i+1));
//        }
//        users.put(5L, new User("admin", "admin", "admin@admin.ru"));
//        users.put(6L, new User("qwerty", "qwerty", "test@password.ru"));
//        return users;
//    }

//    public static Map<Long, Ticket> createTickets() {
//        Map<Long, Ticket> tickets = new HashMap<>();
//        for (int i = 1; i < 51; i++) {
//            User user = null;
//            Event event = createEvents().get(1L);
//            LocalDateTime time = DATE_TIME_NOW.plusDays(1);
//            Ticket ticket = new Ticket(user, event, time, i);
//        }
//
//        return tickets;
//    }
}
