package edu.school21.sockets.services;

import java.sql.SQLException;

public interface UsersService {
    String singUp(String username, String password) throws SQLException;
}
