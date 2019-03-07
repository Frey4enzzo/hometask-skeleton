package ua.epam.spring.hometask.dao.impl;

import ua.epam.spring.hometask.dao.EventDao;
import ua.epam.spring.hometask.domain.Event;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static ua.epam.spring.hometask.util.DateUtils.isEventInDaysRange;
import static ua.epam.spring.hometask.util.SmartUtils.isEmpty;

public class EventDaoImpl implements EventDao {

    private static Map<Long, Event> events = new HashMap<>();

    @Override
    public Event save(@Nonnull Event object) {
        if (events.values().contains(object)) return object;
        events.put(Long.valueOf(events.size()), object);
        return object;
    }

    @Override
    public void remove(@Nonnull Event object) {
        events.values().removeIf(event -> event.equals(object));
    }

    @Override
    public Event getById(@Nonnull Long id) {
        return isEmpty(events.get(id)) ? null : events.get(id);
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        return events.size() > 0 ? events.values() : new ArrayList<>();
    }

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
//        return events.values().stream().filter(event -> event.getName().equals(name)).findAny().orElse(null);
       Optional<Event> optionalValue = events.values().stream().filter(event -> event.getName().equals(name)).findAny();
       return optionalValue.orElseGet(() -> optionalValue.orElse(null));
    }

    @Nonnull
    @Override
    public Set<Event> getForDateRange(@Nonnull LocalDate from, @Nonnull LocalDate to) {
        return events.values().stream().filter(event -> isEventInDaysRange(from, to, event)).collect(Collectors.toSet());
    }

    @Nonnull
    @Override
    public Set<Event> getNextEvents(@Nonnull LocalDateTime to) {
        return null;
    }
}
