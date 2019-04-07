package ua.epam.spring.hometask.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.AuditoriumService;
import ua.epam.spring.hometask.service.UserService;
import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;

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
    AuditoriumService auditoriumService;


    @GetMapping(value = "/users")
    @ResponseBody
    public Collection<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping(value = "/user/{userId}")
    public User getUser(@PathVariable("userId") Long userId) {
        return userService.getById(userId);
    }

    @PostMapping(value = "/user/add", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            StringBuilder errorMess = new StringBuilder();
            errorMess.append(defaultMessageSource.getMessage(USER_FAILED_VALIDATION, null, LocaleContextHolder.getLocale()));
            errors.getAllErrors().forEach(err -> errorMess.append(err.getDefaultMessage()).append("; "));
            log.info(errorMess.toString());
            return new ResponseEntity(errorMess.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        userService.save(user);
        log.info(defaultMessageSource.getMessage(USER_SUCCESS_CREATE, null, LocaleContextHolder.getLocale()));
        auditoriumService.getAll().forEach(v -> log.info(v.toString()));
        return ResponseEntity.ok(defaultMessageSource.getMessage(USER_SUCCESS_CREATE, null, LocaleContextHolder.getLocale()));
    }
}
