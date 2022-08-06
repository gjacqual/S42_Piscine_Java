package school21.spring.service.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

import java.sql.SQLException;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws SQLException {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        UsersRepository usersRepositoryJdbc= context.getBean("usersRepositoryJdbc", UsersRepository.class);
        UsersRepository usersRepositoryJdbcTemplate = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
        System.out.println("Find All with usersRepositoryJdbc:");
        System.out.println(usersRepositoryJdbc.findAll() );

        System.out.println("Find All with usersRepositoryJdbcTemplate:");
        System.out.println(usersRepositoryJdbcTemplate.findAll() );

        System.out.println("!!! usersRepositoryJdbc Implementation !!! ");
        System.out.print(">>>findById: ");
        System.out.println(usersRepositoryJdbc.findById(2L) );
        System.out.print(">>>findByEmail: ");
        Optional<User> testFindByEmail= usersRepositoryJdbc.findByEmail("LenaTest@edu.school21.ru");
        if (testFindByEmail.isPresent()) {
            System.out.println(testFindByEmail.get() );
        } else {
            System.out.println("User not found!");
        }
        User testNewUser = new User(null, "new_address@my.address.com");
        System.out.print(">>>SaveUser: ");
        System.out.println(testNewUser);
        usersRepositoryJdbc.save(testNewUser);
        System.out.println(usersRepositoryJdbc.findAll() );
        System.out.print(">>>UpdateUser: ");
        testNewUser = usersRepositoryJdbc.findByEmail("new_address@my.address.com").get();
        testNewUser.setEmail("UPDATED@mail.com");
        usersRepositoryJdbc.update(testNewUser);
        System.out.println(usersRepositoryJdbc.findAll() );
        System.out.print(">>>DeleteUser: ");
        usersRepositoryJdbc.delete(testNewUser.getIdentifier());
        System.out.println(usersRepositoryJdbc.findAll() );

        System.out.print("----------------------------");
        System.out.println("!!! usersRepositoryJdbcTemplate Implementation !!! ");
        System.out.print(">>>findById: ");
        System.out.println(usersRepositoryJdbcTemplate.findById(2L) );
        System.out.print(">>>findByEmail: ");
        Optional<User> testFindByEmailTemplate= usersRepositoryJdbcTemplate.findByEmail("LenaTest@edu.school21.ru");
        if (testFindByEmailTemplate.isPresent()) {
            System.out.println(testFindByEmailTemplate.get() );
        } else {
            System.out.println("User not found!");
        }
        User testNewUserTemplate = new User(null, "new_address@my.address.com");
        System.out.print(">>>SaveUser: ");
        System.out.println(testNewUserTemplate);
        usersRepositoryJdbcTemplate.save(testNewUserTemplate);
        System.out.println(usersRepositoryJdbcTemplate.findAll() );
        System.out.print(">>>UpdateUser: ");
        testNewUserTemplate = usersRepositoryJdbcTemplate.findByEmail("new_address@my.address.com").get();
        testNewUserTemplate.setEmail("UPDATED@mail.com");
        usersRepositoryJdbcTemplate.update(testNewUserTemplate);
        System.out.println(usersRepositoryJdbcTemplate.findAll() );
        System.out.print(">>>DeleteUser: ");
        usersRepositoryJdbcTemplate.delete(testNewUserTemplate.getIdentifier());
        System.out.println(usersRepositoryJdbcTemplate.findAll() );

        ((ConfigurableApplicationContext)context).close();
    }
}