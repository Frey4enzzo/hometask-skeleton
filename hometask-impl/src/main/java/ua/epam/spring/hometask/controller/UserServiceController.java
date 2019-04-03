package ua.epam.spring.hometask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.UserService;
import java.util.Collection;

@RestController
public class UserServiceController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    @ResponseBody
    public Collection<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping(value = "/user/{userId}")
    public User getUser(@PathVariable("userId") Long userId) {
        return userService.getById(userId);
    }

    @PostMapping(value = "/user/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody User user) {
        userService.save(user);
    }
}
