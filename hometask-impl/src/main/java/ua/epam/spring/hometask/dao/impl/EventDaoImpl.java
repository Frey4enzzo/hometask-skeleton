package ua.epam.spring.hometask.dao.impl;

import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.dao.EventDao;
import ua.epam.spring.hometask.domain.Event;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import static ua.epam.spring.hometask.util.SmartUtils.isEmpty;

@Repository
public class EventDaoImpl implements EventDao {

    private static Map<Long, Event> events = null;

    @Override
    public void save(@Nonnull Event object) {
        if (!events.values().contains(object)) events.put(Long.valueOf(events.size()), object);
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
        return new ArrayList<>(events.values());
    }

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
       Optional<Event> optionalValue = events.values().stream().filter(event -> event.getName().equals(name)).findAny();
       return optionalValue.orElseGet(() -> optionalValue.orElse(null));
    }

    @Nonnull
    @Override
    public Set<Event> getForDateRange(@Nonnull LocalDate from, @Nonnull LocalDate to) {
        return events.values().stream().filter(event -> event.airsOnDates(from, to)).collect(Collectors.toSet());
    }

    @Nonnull
    @Override
    public Set<Event> getNextEvents(@Nonnull LocalDateTime to) {
        return events.values().stream().filter(event -> event.airsOnDateTime(to)).collect(Collectors.toSet());
    }
}
