package ua.epam.spring.hometask.repository;

import org.springframework.data.repository.CrudRepository;
import ua.epam.spring.hometask.domain.Event;

public interface EventRepository extends CrudRepository<Event, Long> {

    Event findByName(String name);
}
