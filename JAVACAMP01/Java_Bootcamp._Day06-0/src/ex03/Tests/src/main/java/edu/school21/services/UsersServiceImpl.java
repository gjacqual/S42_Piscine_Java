package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

public class UsersServiceImpl {
    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    boolean authenticate(String login, String password) throws AlreadyAuthenticatedException {
        User user = usersRepository.findByLogin(login);

        if (user == null) {
            throw new EntityNotFoundException("User not found");
        }
        if (user.getAuthenticated()) {
            throw new AlreadyAuthenticatedException("Error: User Already Authenticated");
        }
        boolean isAuthenticated = user.getPassword().equals(password);
        if (isAuthenticated) {
            user.setAuthenticated(true);
            usersRepository.update(user);
        }
        return isAuthenticated;
    }
}
