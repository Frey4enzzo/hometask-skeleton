package ua.epam.spring.hometask.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface Constants {

    final String AUDITORIUM_RED = "Red_Auditorium";
    final String AUDITORIUM_GREEN = "Green_Auditorium";
    final String AUDITORIUM_BLUE = "Blue_Auditorium";

    interface DateTimeConstants {
        final LocalDateTime DATE_TIME_NOW = LocalDateTime.now();
        final LocalDateTime DEFAULT_TIME = LocalDateTime.parse("01.01.2019 18:00", DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        final LocalDateTime DAY_BEFORE_NOW = DATE_TIME_NOW.minusDays(1);
        final LocalDateTime DAY_AFTER_NOW = DATE_TIME_NOW.plusDays(1);
        final LocalDateTime TWO_DAYS_BEFORE_NOW = DATE_TIME_NOW.minusDays(2);
        final LocalDateTime TWO_DAYS_AFTER_NOW = DATE_TIME_NOW.plusDays(2);
        final LocalDateTime THREE_DAYS_BEFORE_NOW = DATE_TIME_NOW.minusDays(3);
        final LocalDateTime THREE_DAYS_AFTER_NOW = DATE_TIME_NOW.plusDays(3);
        final LocalDateTime FOUR_DAYS_BEFORE_NOW = DATE_TIME_NOW.minusDays(4);
        final LocalDateTime FOUR_DAYS_AFTER_NOW = DATE_TIME_NOW.plusDays(4);
        final LocalDateTime WEEK_BEFORE_NOW = DATE_TIME_NOW.minusWeeks(1);
        final LocalDateTime WEEK_AFTER_NOW = DATE_TIME_NOW.plusWeeks(1);
    }

}
