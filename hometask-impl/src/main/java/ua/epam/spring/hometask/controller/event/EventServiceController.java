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
        Optional<Event> event = Optional.ofNullable(eventService.getById(eventId));
        if (event.isPresent()) return ResponseEntity.of(event);
        return ResponseEntity.notFound().build();
    }
}
