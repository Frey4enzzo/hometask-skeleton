package ua.epam.spring.hometask.repository;

import org.springframework.data.repository.CrudRepository;
import ua.epam.spring.hometask.domain.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Long> {

}
