package ua.epam.spring.hometask.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
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
@Entity
@Table(name = "air_dates")
public class AirDate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_airdate_id")
    private Long id;

    private LocalDateTime airDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
}
