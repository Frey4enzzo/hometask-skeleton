package ua.epam.spring.hometask.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(exclude = "event")
@ToString(exclude = "event")
@NoArgsConstructor
@Entity
@Table(name = "air_dates")
public class AirDate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_airdate_id")
    private Long id;

    private LocalDateTime airDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    @JsonBackReference
    private Event event;

    public AirDate(LocalDateTime airDate) {
        this.airDate = airDate;
    }

    public AirDate(LocalDateTime airDate, Event event) {
        this.airDate = airDate;
        this.event = event;
    }
}
