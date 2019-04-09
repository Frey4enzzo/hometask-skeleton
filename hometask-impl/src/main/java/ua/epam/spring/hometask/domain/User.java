package ua.epam.spring.hometask.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.epam.spring.hometask.validation.annotations.Email;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.Collection;
import java.util.NavigableSet;
import java.util.TreeSet;

@Data
public class User extends DomainObject {

    @NotBlank(message = "Поле firstName не может быть пустым")
    private String firstName;

    @NotBlank(message = "Поле lastName не может быть пустым")
    private String lastName;

    @Email
    private String email;

    private NavigableSet<Ticket> tickets = new TreeSet<>();

    @JsonCreator
    public User(@JsonProperty(value = "firstName") String firstName,
                @JsonProperty(value = "lastName") String lastName,
                @JsonProperty(value = "email") String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
