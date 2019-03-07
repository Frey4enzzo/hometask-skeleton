package ua.epam.spring.hometask.util;

import ua.epam.spring.hometask.domain.Event;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtils {

    public static boolean isEventInDaysRange(LocalDate from, LocalDate to, Event event) {
        LocalDateTime startTime = from.atStartOfDay();
        LocalDateTime endTime = to.atTime(23, 59, 59);

        for (LocalDateTime time : event.getAirDates()) {
            if (time.isAfter(startTime) && time.isBefore(endTime)) return true;
        }
        return false;
    }
}
