package ua.epam.spring.hometask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import java.util.Arrays;

@SpringBootApplication
public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        initializeContext(ctx);


        ctx.close();
    }

    private static void initializeContext(ApplicationContext ctx) {
        logger.info("The beans you were looking for:");
        Arrays.stream(ctx.getBeanDefinitionNames()).forEach(logger::info);
    }
}
