package ua.epam.spring.hometask.util;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import java.time.LocalDateTime;
import java.util.*;

import static ua.epam.spring.hometask.util.Constants.DateTimeConstants.*;

public class DataGenerator {

    public static Map<Long, User> createUsers() {
        Map<Long, User> users = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            users.put(Long.valueOf(i), new User("User FirstName " + i+1, "User LastName " + i+1, "User Email " + i+1));
        }
        users.put(5L, new User("admin", "admin", "admin@admin.ru"));
        users.put(6L, new User("qwerty", "qwerty", "test@password.ru"));
        return users;
    }

    public static Map<Long, Event> createEvents() {
        Map<Long, Event> events = new HashMap<>();

        Event event1 = new Event();
        event1.setName("Football: Real Madrid - Barcelona");
        event1.setAirDates(new TreeSet<LocalDateTime>(Collections.singleton(DATE_TIME_NOW.plusDays(1))));
        event1.setBasePrice(100);
        event1.setRating(EventRating.LOW);
        events.put(1L, event1);

        Event event2 = new Event();
        event2.setName("The game of thrones");
        NavigableSet<LocalDateTime> airDates2 = new TreeSet<>();
        airDates2.add(DATE_TIME_NOW);
        airDates2.add(DAY_AFTER_NOW);
        airDates2.add(TWO_DAYS_AFTER_NOW);
        airDates2.add(DAY_BEFORE_NOW);
        airDates2.add(TWO_DAYS_BEFORE_NOW);
        event2.setAirDates(airDates2);
        event2.setBasePrice(20.95);
        event2.setRating(EventRating.HIGH);
        events.put(2L, event2);

        Event event3 = new Event();
        event3.setName("The International");
        NavigableSet<LocalDateTime> airDates3 = new TreeSet<>();
        airDates3.add(DATE_TIME_NOW.plusHours(3));
        airDates3.add(DAY_AFTER_NOW.plusHours(3));
        airDates3.add(TWO_DAYS_AFTER_NOW.plusHours(3));
        event3.setAirDates(airDates3);
        event3.setBasePrice(49.95);
        event3.setRating(EventRating.MID);
        events.put(3L, event3);

        Event event4 = new Event();
        event4.setName("Rock Live");
        NavigableSet<LocalDateTime> airDates4 = new TreeSet<>();
        airDates4.add(DAY_AFTER_NOW.plusHours(2));
        airDates4.add(TWO_DAYS_AFTER_NOW.plusHours(2));
        airDates4.add(THREE_DAYS_AFTER_NOW.plusHours(4));
        airDates4.add(WEEK_AFTER_NOW.plusHours(1));
        event4.setAirDates(airDates4);
        event4.setBasePrice(62.49);
        event4.setRating(EventRating.MID);
        events.put(4L, event4);

        return events;
    }

    public static Map<Long, Ticket> createTickets() {
        Map<Long, Ticket> tickets = new HashMap<>();
        for (int i = 1; i < 51; i++) {
            User user = null;
            Event event = createEvents().get(1L);
            LocalDateTime time = DATE_TIME_NOW.plusDays(1);
            Ticket ticket = new Ticket(user, event, time, i);
        }

        return tickets;
    }
}
