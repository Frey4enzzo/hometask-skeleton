package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.repository.UserRepository;
import ua.epam.spring.hometask.service.UserService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(@Nonnull User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(@Nonnull User user) {
        userRepository.delete(user);
    }

    @Override
    public User getById(@Nonnull Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Nonnull
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}