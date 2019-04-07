package ua.epam.spring.hometask.handler.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
@Slf4j
public class ControllerErrorHandler {

    @Autowired
    private MessageSource defaultMessageSource;

    public ResponseEntity handleUserValidationError(Errors errors, String errorMessage) {
        StringBuilder errorMess = new StringBuilder();
        errorMess.append(defaultMessageSource.getMessage(errorMessage, null, LocaleContextHolder.getLocale()));
        errors.getAllErrors().forEach(err -> errorMess.append(err.getDefaultMessage()).append("; "));
        log.info(errorMess.toString());
        return new ResponseEntity(errorMess.toString(), BAD_REQUEST);
    }
}
