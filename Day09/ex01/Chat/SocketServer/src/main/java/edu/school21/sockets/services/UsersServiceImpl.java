package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.UUID;

@Component("usersService")
public class UsersServiceImpl implements  UsersService{
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(@Qualifier("jdbcTemplateImpl") UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String singUp(String username, String password) throws SQLException {
        String securedPassword = passwordEncoder.encode(password);
        usersRepository.save( new User(null, username, securedPassword));

        return securedPassword;
    }


}
