package ua.epam.spring.hometask.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;
import java.util.NavigableMap;
import java.util.TreeMap;

@Data
@Entity
@Table(name = "events")
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_event_id")
    private Long id;

    @NotBlank(message = "Название мероприятия должно быть указано")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event", orphanRemoval = true)
    @JsonManagedReference
    private Set<AirDate> airDates = new TreeSet<>();

    private double price;

    @Enumerated(EnumType.STRING)
    private EventRating rating;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private Set<Ticket> tickets = new TreeSet<>();

    @Transient
    private NavigableMap<LocalDateTime, Auditorium> auditoriums = new TreeMap<>();

    /**
     * Checks if event is aired on particular <code>dateTime</code> and assigns
     * auditorium to it.
     * 
     * @param dateTime
     *            Date and time of aired event for which to assign
     * @param auditorium
     *            Auditorium that should be assigned
     * @return <code>true</code> if successful, <code>false</code> if event is
     *         not aired on that date
     */
    public boolean assignAuditorium(LocalDateTime dateTime, Auditorium auditorium) {
        if (airDates.contains(dateTime)) {
            auditoriums.put(dateTime, auditorium);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Removes auditorium assignment from event
     * 
     * @param dateTime
     *            Date and time to remove auditorium for
     * @return <code>true</code> if successful, <code>false</code> if not
     *         removed
     */
    public boolean removeAuditoriumAssignment(LocalDateTime dateTime) {
        return auditoriums.remove(dateTime) != null;
    }

    /**
     * Add date and time of event air
     * 
     * @param dateTime
     *            Date and time to add
     * @return <code>true</code> if successful, <code>false</code> if already
     *         there
     */
    public boolean addAirDateTime(AirDate dateTime) {
        return airDates.add(dateTime);
    }

    /**
     * Adding date and time of event air and assigning auditorium to that
     * 
     * @param dateTime
     *            Date and time to add
     * @param auditorium
     *            Auditorium to add if success in date time add
     * @return <code>true</code> if successful, <code>false</code> if already
     *         there
     */
    public boolean addAirDateTime(AirDate dateTime, Auditorium auditorium) {
        boolean result = airDates.add(dateTime);
        if (result) {
            auditoriums.put(dateTime.getAirDate(), auditorium);
        }
        return result;
    }

    /**
     * Removes the date and time of event air. If auditorium was assigned to
     * that date and time - the assignment is also removed
     * 
     * @param dateTime
     *            Date and time to remove
     * @return <code>true</code> if successful, <code>false</code> if not there
     */
    public boolean removeAirDateTime(AirDate dateTime) {
        boolean result = airDates.remove(dateTime);
        if (result) {
            auditoriums.remove(dateTime);
        }
        return result;
    }

    /**
     * Checks if event airs on particular date and time
     * 
     * @param dateTime
     *            Date and time to check
     * @return <code>true</code> event airs on that date and time
     */
    public boolean airsOnDateTime(LocalDateTime dateTime) {
        return airDates.stream().anyMatch(dt -> dt.equals(dateTime));
    }

    /**
     * Checks if event airs on particular date
     * 
     * @param date
     *            Date to ckeck
     * @return <code>true</code> event airs on that date
     */
    public boolean airsOnDate(LocalDate date) {
        return airDates.stream().anyMatch(dt -> dt.getAirDate().toLocalDate().equals(date));
    }

    /**
     * Checking if event airs on dates between <code>from</code> and
     * <code>to</code> inclusive
     * 
     * @param from
     *            Start date to check
     * @param to
     *            End date to check
     * @return <code>true</code> event airs on dates
     */
    public boolean airsOnDates(LocalDate from, LocalDate to) {
        return airDates.stream()
                .anyMatch(dt -> dt.getAirDate().toLocalDate().compareTo(from) >= 0 && dt.getAirDate().toLocalDate().compareTo(to) <= 0);
    }

    public boolean airsOnDates(LocalDateTime from, LocalDateTime to) {
        return airDates.stream()
                .anyMatch(dt -> dt.getAirDate().isAfter(from) && dt.getAirDate().isBefore(to));
    }
}
