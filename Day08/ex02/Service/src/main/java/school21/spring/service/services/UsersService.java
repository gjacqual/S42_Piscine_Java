package school21.spring.service.services;

import java.sql.SQLException;

public interface UsersService {
    String singUp(String email) throws SQLException;
}
