package ua.epam.spring.hometask.validation.validators;

import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.User;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class UserValidator implements DomainValidator<User> {

    private Validator validator;
    private Set<ConstraintViolation<User>> violations;

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    @Override
    public void validate(User user) {
        violations = validator.validate(user);
    }

    @Override
    public Set<ConstraintViolation<User>> getValidationResult() {
        return violations;
    }

    @Override
    public void logResult() {
        violations.forEach(v -> System.out.println(v.getMessage()));
    }
}
