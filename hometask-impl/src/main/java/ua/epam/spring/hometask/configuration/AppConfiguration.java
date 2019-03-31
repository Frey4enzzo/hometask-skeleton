package ua.epam.spring.hometask.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(basePackages = {"ua.epam.spring.hometask"})
@ImportResource({"auditoriums.xml"})
public class AppConfiguration {

}
