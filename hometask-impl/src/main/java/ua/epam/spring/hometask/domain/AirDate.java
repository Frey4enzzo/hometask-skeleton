package ua.epam.spring.hometask.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(exclude = "event")
@ToString(exclude = "event")
@NoArgsConstructor
@Entity
@Table(name = "air_dates")
public class AirDate implements Comparable<AirDate>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_airdate_id")
    private Long id;

    private LocalDateTime airDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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

    @Override
    public int compareTo(AirDate other) {
        if (this.airDate.equals(other)) return 0;
        if (this.airDate.isAfter(other.airDate)) return 1;
        return -1;
    }
}
