package ua.epam.spring.hometask.repository;

import org.springframework.data.repository.CrudRepository;
import ua.epam.spring.hometask.domain.AirDate;

import java.util.List;
import java.util.Optional;

public interface AirDateRepository extends CrudRepository<AirDate, Long> {

    Optional<AirDate> findById(Long id);

    List<AirDate> findAll();
}
