package ua.epam.spring.hometask.controller.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.service.EventService;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@RestController
@Slf4j
@RequestMapping(value = "/events")
public class EventServiceController {

    @Autowired
    private EventService eventService;

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
