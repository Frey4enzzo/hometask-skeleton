package ua.epam.spring.hometask.service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import ua.epam.spring.hometask.domain.AirDate;
import ua.epam.spring.hometask.domain.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface EventService {

    Event getById(Long id);

    /**
     * Finding event by name
     * 
     * @param name
     *            Name of the event
     * @return found event or <code>null</code>
     */
    @Nullable Event getByName(@Nonnull String name);

     /**
     * Finding all events that air on specified date range
     * 
     * @param from Start date
     * 
     * @param to End date inclusive
     * 
     * @return Set of events
     */
     public @Nonnull Set<Event> getForDateRange(@Nonnull LocalDate from, @Nonnull LocalDate to);

     /**
     * Return events from 'now' till the the specified date time
     * 
     * @param to End date time inclusive
     * s
     * @return Set of events
     */
     public @Nonnull Set<Event> getNextEvents(@Nonnull LocalDateTime to);

     List<Event> getAll();

     void delete(@Nonnull Event event);

     Event save(Event event);

     void addAirDate(Long eventId, LocalDateTime airDate);

     void deleteAirDate(Long eventId, LocalDateTime airDate);

}
