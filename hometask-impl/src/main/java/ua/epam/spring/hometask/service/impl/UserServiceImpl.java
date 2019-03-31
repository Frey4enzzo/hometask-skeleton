package ua.epam.spring.hometask.service.impl;

import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.UserService;
import ua.epam.spring.hometask.validation.validators.DomainValidator;
import java.util.Collection;

public class UserServiceImpl implements UserService {

    UserDao userDao;
    DomainValidator<User> domainValidator;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setDomainValidator(DomainValidator<User> domainValidator) {
        this.domainValidator = domainValidator;
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public void save(User user) {
        domainValidator.validate(user);
        if (domainValidator.getValidationResult().size() == 0) userDao.save(user);
        else domainValidator.logResult();
    }

    @Override
    public void remove(User object) {
        userDao.remove(object);
    }

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public Collection<User> getAll() {
        return userDao.getAll();
    }
}
