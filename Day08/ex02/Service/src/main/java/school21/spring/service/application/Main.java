package school21.spring.service.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.config.ApplicationConfig;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.services.UsersService;

import java.sql.SQLException;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws SQLException {
        ApplicationContext context = new
                AnnotationConfigApplicationContext(ApplicationConfig.class);
        UsersService usersService = context.getBean(
                "usersService1", UsersService.class);
        System.out.println(">>> UsersServiceTest <<<");
        String password = usersService.singUp("newTest@nevermail.com");
        System.out.println(password);

        ((ConfigurableApplicationContext)context).close();
    }
}