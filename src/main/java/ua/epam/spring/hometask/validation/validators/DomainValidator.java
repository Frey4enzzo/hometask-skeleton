package ua.epam.spring.hometask.validation.validators;

import javax.validation.ConstraintViolation;
import java.util.Set;

public interface DomainValidator<T> {

    void validate(T  t);

    Set<ConstraintViolation<T>> getValidationResult();

    void logResult();
}
