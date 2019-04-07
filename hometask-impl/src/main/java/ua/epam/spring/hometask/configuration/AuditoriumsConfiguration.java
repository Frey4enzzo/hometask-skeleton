package ua.epam.spring.hometask.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.InitBinder;
import ua.epam.spring.hometask.domain.Auditorium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@PropertySource("classpath:/auditories/auditorium.properties")
public class AuditoriumsConfiguration {

    @Autowired
    Environment environment;

    @Bean
    public Auditorium redAuditorium() {
        Auditorium auditorium = new Auditorium();
        auditorium.setName(environment.getProperty("auditorium.red.name"));
        auditorium.setNumberOfSeats(Long.parseLong(environment.getProperty("auditorium.red.seat")));
        Set<Long> vips = (Arrays.asList(environment.getProperty("auditorium.red.vip").split(",")).stream().map(
                seat -> Long.parseLong(seat))).collect(Collectors.toSet());
        auditorium.setVipSeats(vips);
        return auditorium;
    }

    @Bean
    public Auditorium greenAuditorium() {
        Auditorium auditorium = new Auditorium();
        auditorium.setName(environment.getProperty("auditorium.green.name"));
        auditorium.setNumberOfSeats(Long.parseLong(environment.getProperty("auditorium.green.seat")));
        Set<Long> vips = (Arrays.asList(environment.getProperty("auditorium.green.vip").split(",")).stream().map(
                seat -> Long.parseLong(seat))).collect(Collectors.toSet());
        auditorium.setVipSeats(vips);
        return auditorium;
    }

    @Bean
    public Auditorium blueAuditorium() {
        Auditorium auditorium = new Auditorium();
        auditorium.setName(environment.getProperty("auditorium.blue.name"));
        auditorium.setNumberOfSeats(Long.parseLong(environment.getProperty("auditorium.blue.seat")));
        Set<Long> vips = (Arrays.asList(environment.getProperty("auditorium.blue.vip").split(",")).stream().map(
                seat -> Long.parseLong(seat))).collect(Collectors.toSet());
        auditorium.setVipSeats(vips);
        return auditorium;
    }
}
