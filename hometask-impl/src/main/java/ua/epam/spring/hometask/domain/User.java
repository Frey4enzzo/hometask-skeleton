package ua.epam.spring.hometask.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.epam.spring.hometask.validation.annotations.Email;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.NavigableSet;
import java.util.TreeSet;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_user_id")
    private Long id;

    @NotBlank(message = "Поле firstName не может быть пустым")
    private String firstName;

    @NotBlank(message = "Поле lastName не может быть пустым")
    private String lastName;

    @Email
    private String email;

    @Transient
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
