package ua.epam.spring.hometask.dao.impl;

import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.User;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ua.epam.spring.hometask.util.SmartUtils.isEmpty;

public class UserDaoImpl implements UserDao {

    private static Map<Long, User> users = new HashMap<>();

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        List<User> filterUsers = users.values().stream().filter(user -> user.getEmail().equals(email)).collect(Collectors.toList());
        return isEmpty(filterUsers) ? null : filterUsers.get(0);
    }

    @Override
    public User save(@Nonnull User object) {
        if (users.values().contains(object)) return object;
        users.put(Long.valueOf(users.size()), object);
        return object;
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
        return users.values();
    }
}