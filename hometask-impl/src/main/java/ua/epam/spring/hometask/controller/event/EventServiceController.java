package ua.epam.spring.hometask.controller.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.handler.error.ControllerErrorHandler;
import ua.epam.spring.hometask.service.EventService;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import static java.util.Optional.ofNullable;
import static ua.epam.spring.hometask.controller.event.EventControllerMessages.EVENT_FAILED_VALIDATION;
import static ua.epam.spring.hometask.controller.event.EventControllerMessages.EVENT_SUCCESS_CREATE;

@RestController
@Slf4j
@RequestMapping(value = "/events")
public class EventServiceController {

    @Autowired
    private EventService eventService;
    @Autowired
    private MessageSource defaultMessageSource;
    @Autowired
    ControllerErrorHandler controllerErrorHandler;

    @GetMapping(produces = "application/json")
    public List<Event> getAllEvents() {
        return eventService.getAll();
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
        eventService.save(event);
        return ResponseEntity.ok(defaultMessageSource.getMessage(EVENT_SUCCESS_CREATE, null, LocaleContextHolder.getLocale()));
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


}
