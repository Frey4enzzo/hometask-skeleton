package ua.epam.spring.hometask.dao;

import org.springframework.data.repository.CrudRepository;
import ua.epam.spring.hometask.domain.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findById(Long id);
}
