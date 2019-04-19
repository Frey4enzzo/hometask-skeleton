package ua.epam.spring.hometask.service.impl;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.AirDate;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.repository.AirDateRepository;
import ua.epam.spring.hometask.repository.EventRepository;
import ua.epam.spring.hometask.service.EventService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
@Slf4j
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;
    @Autowired
    AirDateRepository airDateRepository;

    public Event getById(@Nonnull Long id) {
        return ofNullable(eventRepository.findById(id)).orElseGet(null).get();
    }

    @Nullable
    public Event getByName(@Nonnull String name) {
        return ofNullable(eventRepository.findByName(name)).get();
    }

    @Nonnull
    @Override
    public Set<Event> getForDateRange(@Nonnull LocalDate from, @Nonnull LocalDate to) {
        log.info("Показать события c: {} по: {}", from, to);
        return getAll().stream().filter(event -> event.airsOnDates(from, to)).collect(Collectors.toSet());
    }

    @Nonnull
    @Override
    public Set<Event> getNextEvents(@Nonnull LocalDateTime to) {
        log.info("Показать следующие события с текущшей даты по: {}", to);
        return getAll().stream().filter(event -> event.airsOnDates(LocalDateTime.now(), to)).collect(Collectors.toSet());
    }

    @Override
    public List<Event> getAll() {
        return Lists.newArrayList(eventRepository.findAll());
    }

    public void delete(Event event) {
        eventRepository.delete(event);
        log.info("Событие удалено: {}", event);
    }

    @Override
    public Event save(Event event) {
        Event newEvent = eventRepository.findByName(event.getName());
        if (newEvent == null) return eventRepository.save(event);
        return null;
    }

    @Override
    public void addAirDate(Long eventId, LocalDateTime airDate) {
        Optional<Event> event = ofNullable(eventRepository.findById(eventId)).get();
        AirDate date = airDateRepository.save(new AirDate(airDate, event.get()));
        event.get().addAirDateTime(date);
        log.info("Новая дата успешно добавлена к событию {}", event.get());
    }

    @Override
    public void deleteAirDate(Long eventId, LocalDateTime airDate) {
        Optional<Event> event = ofNullable(eventRepository.findById(eventId)).get();
        AirDate date = airDateRepository.findByAirDate(airDate);
        if (event.get().getAirDates().contains(date)) {
            event.get().removeAirDateTime(date);
            airDateRepository.delete(date);
            log.info("Дата показа успешно удалена из события: ", event.get());
        } else {
            log.info("Удаление даты невозможно, дата не была запланирована: ", event.get());
        }
    }
}
