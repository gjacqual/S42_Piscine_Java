package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    private final DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public User findById(Long id) throws SQLException {
        final String sql_query = "SELECT * FROM users WHERE identifier = ?;";
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql_query);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) {
            return null;
        }
        User user = new User(
                resultSet.getLong("identifier"),
                resultSet.getString("email"));
        preparedStatement.close();
        connection.close();
        return  user;
    }

    @Override
    public List<User> findAll() throws SQLException{
        final String sql_query = "SELECT * FROM users;";
        List<User> userList = new ArrayList<>();

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql_query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User user = new User(
                    resultSet.getLong("identifier"),
                    resultSet.getString("email")
            );
            userList.add(user);
        }
        preparedStatement.close();
        connection.close();
        return userList;
    }

    @Override
    public void save(User entity) throws SQLException {
        String sql_save = "INSERT INTO users (email) VALUES (?);";
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql_save);
        preparedStatement.setString(1, entity.getEmail());
        preparedStatement.execute();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public void update(User entity) throws SQLException {
        String sql_update = "UPDATE users SET email=? WHERE identifier=?;";
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql_update);
        preparedStatement.setString(1, entity.getEmail());
        preparedStatement.setLong(2, entity.getIdentifier());
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql_delete = "DELETE FROM users WHERE identifier = ?";
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql_delete);
        preparedStatement.setLong(1, id);
        preparedStatement.execute();

        preparedStatement.close();
        connection.close();
    }

    @Override
    public Optional<User> findByEmail(String email) throws SQLException {
        String sql_find_by_email = "SELECT * FROM users WHERE email = ?";
        Optional<User> optionalUser;
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql_find_by_email);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) {
            return Optional.empty();
        }
        User user = new User(
                resultSet.getLong("identifier"),
                resultSet.getString("email"));
        preparedStatement.close();
        connection.close();
        return  Optional.of(user);
    }
}
