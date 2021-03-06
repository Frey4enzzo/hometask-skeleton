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
import java.util.List;
import java.util.Optional;
import static java.util.Optional.ofNullable;
import static ua.epam.spring.hometask.controller.user.UserControllerMessages.*;

@RestController
@Slf4j
@RequestMapping(value = "/users")
public class UserServiceController {

    @Autowired
    private UserService userService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private ControllerErrorHandler controllerErrorHandler;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") Long userId) {
        Optional<User> user = ofNullable(userService.getById(userId));
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/add", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return controllerErrorHandler.handleControllerValidationError(errors, USER_FAILED_VALIDATION, HttpStatus.BAD_REQUEST);
        }
        User savedUser = userService.save(user);
        if (savedUser != null) {
            log.info("Новый пользователь создан: {}", user);
            return ResponseEntity.ok(messageSource.getMessage(USER_SUCCESS_CREATE, null, LocaleContextHolder.getLocale()));
        } else {
            log.info("Такой пользователь уже существует");
            return ResponseEntity.ok(messageSource.getMessage(USER_CREATED_FAILED_EXISTS, null, LocaleContextHolder.getLocale()));
        }
    }

    @GetMapping(value = "/find/{email:.+}")
    public ResponseEntity<User> findByEmail(@PathVariable(value = "email") String email) {
        Optional<User> user = ofNullable(userService.getUserByEmail(email));
        if (user.isPresent()) return ResponseEntity.ok(user.get());
        else return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/delete/{userId}", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId) {
        Optional<User> user = ofNullable(userService.getById(userId));
        if (user.isPresent()) {
            userService.delete(user.get());
            return ResponseEntity.ok("Пользователь удален: " + user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
