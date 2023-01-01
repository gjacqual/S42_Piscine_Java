package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.persistence.EntityNotFoundException;

import static org.mockito.Mockito.*;

public class UsersServiceImplTest {
    private User user;
    private final User correctUser = new User(1L, "Max", "111222333", false);
    private final User incorrectLogin = new User(5L, "Kate", "111222333", false);
    private final User incorrectPassword = new User(1L, "Max", "111222334", false);
    private UsersServiceImpl usersService;
    private UsersRepository usersRepository = Mockito.mock(UsersRepository.class);
    @BeforeEach
    void init() {
        usersService = new UsersServiceImpl(usersRepository);
        when(usersRepository.findByLogin(correctUser.getLogin())).thenReturn(correctUser);
        when(usersRepository.findByLogin(incorrectPassword.getLogin())).thenReturn(correctUser);
        doNothing().when(usersRepository).update(correctUser);
    }

    @Test
    void testCorrectUserPassword() throws AlreadyAuthenticatedException, EntityNotFoundException {
        Assertions.assertTrue(usersService.authenticate(correctUser.getLogin(), correctUser.getPassword()));
        verify(usersRepository).update(correctUser);
    }

    @Test
    void testIncorrectLogin() throws EntityNotFoundException{
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> usersService.authenticate(incorrectLogin.getLogin(), incorrectLogin.getPassword()));
        verify(usersRepository, Mockito.never()).update(incorrectLogin);
    }

    @Test
    void testIncorrectPassword() throws AlreadyAuthenticatedException {
        Assertions.assertFalse(usersService.authenticate(incorrectPassword.getLogin(),
                incorrectPassword.getPassword()));
        verify(usersRepository, Mockito.never()).update(incorrectLogin);
    }
}
