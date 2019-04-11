package ua.epam.spring.hometask.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity(name = "air_dates")
public class AirDate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_event_id")
    private Long id;

    private LocalDateTime airDate;

    @Transient
    private Event event;
}
