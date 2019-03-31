package ua.epam.spring.hometask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.UserService;
import java.util.Collection;

@RestController
public class UserServiceController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Collection<User> getAllUsers() {
        return userService.getAll();
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable("userId") Long userId) {
        return userService.getById(userId);
    }
}
