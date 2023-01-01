package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private final DataSource dataSource;
    private static final String SQL_QUERY = "SELECT * " +
            "FROM messages " +
            "JOIN users u on u.id = messages.author " +
            "JOIN chat_rooms cr on cr.id = messages.room " +
            "WHERE messages.id=?;";
    private static final String SQL_INSERT_MESSAGE = "INSERT INTO messages(" +
            "author, room, text, date_time) " +
            "VALUES  (?, ?, ?, ?)";

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) {

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preStatement = connection.prepareStatement(SQL_QUERY);
            preStatement.setLong(1, id);
            ResultSet resSet = preStatement.executeQuery();
            if (!resSet.next()) {
                return Optional.empty();
            }

            User user = new User(
                    resSet.getInt("author"),
                    resSet.getString("login"),
                    resSet.getString("password"),
                    null,
                    null
            );
            Chatroom chatroom = new Chatroom(
                    resSet.getInt("room"),
                    resSet.getString("name"),
                    null,
                    null
            );

            Message message = new Message(
                    resSet.getInt("id"),
                    user,
                    chatroom,
                    resSet.getString("text"),
                    resSet.getTimestamp("date_time").toLocalDateTime()
            );
            connection.close();
            return Optional.of(message);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("Database Error", e);
        }
    }

    @Override
    public void save(Message message) throws NotSavedSubEntityException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_MESSAGE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, message.getAuthor().getId());
            preparedStatement.setLong(2, message.getRoom().getId());
            preparedStatement.setString(3, message.getText());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(message.getDateTime()));
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            message.setId(resultSet.getInt("id"));

        } catch (NullPointerException | SQLException e) {
            throw new NotSavedSubEntityException();
        }
    }

}
