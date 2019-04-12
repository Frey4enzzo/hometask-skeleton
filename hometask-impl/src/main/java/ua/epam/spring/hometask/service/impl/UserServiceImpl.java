package ua.epam.spring.hometask.service.impl;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.repository.UserRepository;
import ua.epam.spring.hometask.service.UserService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Nullable
    public User getUserByEmail(@Nonnull String email) {
        log.info("Поиск пользователя с параметром email: {}", email);
        return userRepository.findByEmail(email);
    }

    public User save(@Nonnull User user) {
        return userRepository.save(user);
    }

    public void delete(@Nonnull User user) {
        userRepository.delete(user);
    }

    public User getById(@Nonnull Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Nonnull
    public List<User> getAll() {
        return Lists.newArrayList(userRepository.findAll());
    }
}