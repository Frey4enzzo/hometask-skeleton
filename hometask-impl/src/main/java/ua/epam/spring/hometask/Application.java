package ua.epam.spring.hometask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
@Slf4j
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
//        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
//        initializeContext(ctx);
//
//        UserService service = ctx.getBean(UserService.class);
//        service.save(new User("raz", "d", "trii"));
//        System.out.println(service.getUserByEmail("trii"));
//        System.out.println(service.getAll());


    }

    private static void initializeContext(ApplicationContext ctx) {
        log.info("The beans you were looking for:");
        Arrays.stream(ctx.getBeanDefinitionNames()).forEach(log::info);
    }
}
