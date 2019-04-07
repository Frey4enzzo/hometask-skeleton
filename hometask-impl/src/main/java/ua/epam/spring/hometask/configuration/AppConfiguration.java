package ua.epam.spring.hometask.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static java.nio.charset.StandardCharsets.UTF_8;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"ua.epam.spring.hometask"})
@ImportResource({"auditoriums.xml"})
public class AppConfiguration {

    @Bean
    public ResourceBundleMessageSource defaultMessageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("messages/messages");
        source.setDefaultEncoding("UTF-8");
        return source;
    }
}
