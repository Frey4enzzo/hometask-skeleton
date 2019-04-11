package ua.epam.spring.hometask.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity(name = "tickets")
public class Ticket implements Comparable<Ticket> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_ticket_id")
    private Long id;

    @Transient
    private User user;

    @Transient
    private Event event;

    private LocalDateTime dateTime;

    private long seat;

    private boolean onSale;

    public Ticket(User user, Event event, LocalDateTime dateTime, long seat) {
        this.user = user;
        this.event = event;
        this.dateTime = dateTime;
        this.seat = seat;
    }


    @Override
    public int compareTo(Ticket other) {
        if (other == null) {
            return 1;
        }
        int result = dateTime.compareTo(other.getDateTime());

        if (result == 0) {
            result = event.getName().compareTo(other.getEvent().getName());
        }
        if (result == 0) {
            result = Long.compare(seat, other.getSeat());
        }
        return result;
    }

}
