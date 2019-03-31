package ua.epam.spring.hometask.dao.impl;

import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.User;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

import static ua.epam.spring.hometask.util.DataGenerator.createUsers;
import static ua.epam.spring.hometask.util.SmartUtils.isEmpty;

@Repository
public class UserDaoImpl implements UserDao {

    private static Map<Long, User> users = createUsers();

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        List<User> filterUsers = users.values().stream().filter(user -> user.getEmail().equals(email)).collect(Collectors.toList());
        return isEmpty(filterUsers) ? null : filterUsers.get(0);
    }

    @Override
    public void save(@Nonnull User object) {
        if (!users.values().contains(object)) users.put(Long.valueOf(users.size()), object);
    }

    @Override
    public void remove(@Nonnull User object) {
        users.values().removeIf(user -> user.equals(object));
    }

    @Override
    public User getById(@Nonnull Long id) {
        return isEmpty(users.get(id)) ? null : users.get(id);
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        return new ArrayList<>(users.values());
    }
}
