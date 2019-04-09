package ua.epam.spring.hometask.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.handler.error.ControllerErrorHandler;
import ua.epam.spring.hometask.service.UserService;
import javax.validation.Valid;
import java.util.Collection;
import static ua.epam.spring.hometask.controller.user.UserControllerMessages.USER_FAILED_VALIDATION;
import static ua.epam.spring.hometask.controller.user.UserControllerMessages.USER_SUCCESS_CREATE;

@RestController
@Slf4j
public class UserServiceController {

    @Autowired
    private UserService userService;
    @Autowired
    private MessageSource defaultMessageSource;
    @Autowired
    ControllerErrorHandler controllerErrorHandler;

    @GetMapping(value = "/users")
    @ResponseBody
    public Collection<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping(value = "/users/{userId}")
    public User getUser(@PathVariable("userId") Long userId) {
        return userService.getById(userId);
    }

    @PostMapping(value = "/users/add", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return controllerErrorHandler.handleControllerValidationError(errors, USER_FAILED_VALIDATION, HttpStatus.BAD_REQUEST);
        }
        userService.save(user);
        return ResponseEntity.ok(defaultMessageSource.getMessage(USER_SUCCESS_CREATE, null, LocaleContextHolder.getLocale()));
    }
}
