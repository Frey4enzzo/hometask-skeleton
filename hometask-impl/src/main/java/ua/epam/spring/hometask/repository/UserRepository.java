package ua.epam.spring.hometask.repository;

import org.springframework.data.repository.CrudRepository;
import ua.epam.spring.hometask.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);
}
