package ua.epam.spring.hometask.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ua.epam.spring.hometask.validation.annotations.Email;
import javax.validation.constraints.NotBlank;
import java.util.NavigableSet;
import java.util.Objects;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public NavigableSet<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(NavigableSet<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        if (email == null) {
            if (other.email != null) {
                return false;
            }
        } else if (!email.equals(other.email)) {
            return false;
        }
        if (firstName == null) {
            if (other.firstName != null) {
                return false;
            }
        } else if (!firstName.equals(other.firstName)) {
            return false;
        }
        if (lastName == null) {
            if (other.lastName != null) {
                return false;
            }
        } else if (!lastName.equals(other.lastName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
