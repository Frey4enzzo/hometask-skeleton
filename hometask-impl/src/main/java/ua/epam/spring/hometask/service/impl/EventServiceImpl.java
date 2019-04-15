package ua.epam.spring.hometask.service.impl;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.repository.EventRepository;
import ua.epam.spring.hometask.service.EventService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
@Slf4j
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

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
        log.info("Показать следующие события с текущшей даты: {}", to);
        return getAll().stream().filter(event -> event.airsOnDates(LocalDate.now(), to.toLocalDate())).collect(Collectors.toSet());
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
        Event newEvent = eventRepository.save(event);
        log.info("Создано новое событие: {}", newEvent);
        return newEvent;
    }
}
