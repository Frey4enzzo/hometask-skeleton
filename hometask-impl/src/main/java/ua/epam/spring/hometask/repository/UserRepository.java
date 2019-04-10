package ua.epam.spring.hometask.repository;

import org.springframework.data.repository.CrudRepository;
import ua.epam.spring.hometask.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findById(Long id);

    List<User> findAll();

    User save(User user);

    void delete(User user);

    User findByEmail(String email);
}
