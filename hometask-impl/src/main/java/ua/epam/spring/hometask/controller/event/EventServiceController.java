package ua.epam.spring.hometask.controller.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.handler.error.ControllerErrorHandler;
import ua.epam.spring.hometask.service.EventService;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import static java.util.Optional.ofNullable;
import static ua.epam.spring.hometask.controller.event.EventControllerMessages.*;

@RestController
@Slf4j
@RequestMapping(value = "/events")
public class EventServiceController {

    @Autowired
    private EventService eventService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    ControllerErrorHandler controllerErrorHandler;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAll());
    }

    @GetMapping(value = "/{eventId}")
    public ResponseEntity<Event> getEvent(@PathVariable("eventId") Long eventId) {
        Optional<Event> event = ofNullable(eventService.getById(eventId));
        if (event.isPresent()) return ResponseEntity.of(event);
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/add", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> addEvent(@RequestBody @Valid Event event, Errors errors) {
        if (errors.hasErrors()) {
            return controllerErrorHandler.handleControllerValidationError(errors, EVENT_FAILED_VALIDATION, HttpStatus.BAD_REQUEST);
        }
        Event newEvent = eventService.save(event);
        if (newEvent != null) {
            log.info("Новое событие создано: {}", event);
            return ResponseEntity.ok(messageSource.getMessage(EVENT_SUCCESS_CREATE, null, LocaleContextHolder.getLocale()));
        } else {
            return ResponseEntity.ok(messageSource.getMessage(EVENT_CREATED_FAILED_EXISTS, null, LocaleContextHolder.getLocale()));
        }
    }

    @GetMapping(value = "/delete/{eventId}", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> deleteEvent(@PathVariable("eventId") Long eventId) {
        Optional<Event> event = ofNullable(eventService.getById(eventId));
        if (event.isPresent()) {
            eventService.delete(event.get());
            return ResponseEntity.ok("Мероприятие удалено: " + event.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/next/{date}", produces = "application/json")
    public ResponseEntity<Set<Event>> getNextEvents(@PathVariable(name = "date")
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return ResponseEntity.ok(eventService.getNextEvents(date));
    }

    @GetMapping(value = "/range")
    public ResponseEntity<Set<Event>> getEventsForDateRange(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(eventService.getForDateRange(startDate, endDate));
    }

    @PostMapping(value = "/add/{eventId}/airdate/{date}", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> addAirDateForEvent(@PathVariable(name = "eventId") Long eventId,
                                                     @PathVariable(name = "date")
                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date ) {

        eventService.addAirDate(eventId, date);
        return ResponseEntity.ok("Новая дата успешно добавлена к событию");
    }

    @PostMapping(value = "/delete/{eventId}/airdate/{date}", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> deleteAirDateForEvent(@PathVariable(name = "eventId") Long eventId,
                                                     @PathVariable(name = "date")
                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date ) {

        eventService.deleteAirDate(eventId, date);
        return ResponseEntity.ok("Дата успешно удалена");
    }
}
