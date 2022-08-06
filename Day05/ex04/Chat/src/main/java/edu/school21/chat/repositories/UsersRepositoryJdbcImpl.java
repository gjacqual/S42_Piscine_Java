package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private static final String SQL_ALL = "SELECT *" +
            "FROM users OFFSET ? LIMIT ?";
    private final DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> findAll(int page, int size) {
        List<User> usersList = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preStatement = connection.prepareStatement(SQL_ALL);
            preStatement.setInt(1, page * size);
            preStatement.setInt(2, size);
            ResultSet resultSet = preStatement.executeQuery();

            while (resultSet.next()) {
                usersList.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        new ArrayList<Chatroom>(),
                        new ArrayList<Chatroom>()
                ));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }



        return usersList;
    }
}
