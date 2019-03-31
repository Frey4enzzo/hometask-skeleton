package ua.epam.spring.hometask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.EventService;
import ua.epam.spring.hometask.service.UserService;

import java.util.Arrays;

@SpringBootApplication
public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        initializeContext(ctx);

        UserService service = ctx.getBean(UserService.class);
        service.save(new User("raz", "d", "trii"));
        System.out.println(service.getUserByEmail("trii"));

        ctx.close();
    }

    private static void initializeContext(ApplicationContext ctx) {
        logger.info("The beans you were looking for:");
        Arrays.stream(ctx.getBeanDefinitionNames()).forEach(logger::info);
    }
}
