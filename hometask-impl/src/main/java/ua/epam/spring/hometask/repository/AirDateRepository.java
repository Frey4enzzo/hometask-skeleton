package ua.epam.spring.hometask.repository;

import org.springframework.data.repository.CrudRepository;
import ua.epam.spring.hometask.domain.AirDate;

import java.time.LocalDateTime;

public interface AirDateRepository extends CrudRepository<AirDate, Long> {

    AirDate findByAirDate(LocalDateTime dateTime);
}
