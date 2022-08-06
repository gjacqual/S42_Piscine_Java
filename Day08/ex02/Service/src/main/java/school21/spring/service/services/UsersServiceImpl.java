package school21.spring.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

import java.sql.SQLException;
import java.util.UUID;

@Component("usersService1")
public class UsersServiceImpl implements  UsersService{
    private UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(@Qualifier("usersRepositoryJdbcTemplateImpl") UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public String singUp(String email) throws SQLException {
        UUID uuid = UUID.randomUUID();
        String password = uuid.toString().substring(0, 8);
        usersRepository.save( new User(null, email, password));

        return password.toString();
    }


}
