package ua.epam.spring.hometask.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"event", "user"})
@ToString(exclude = {"event", "user"})
@Entity
@Table(name = "tickets")
public class Ticket implements Comparable<Ticket> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_ticket_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
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
