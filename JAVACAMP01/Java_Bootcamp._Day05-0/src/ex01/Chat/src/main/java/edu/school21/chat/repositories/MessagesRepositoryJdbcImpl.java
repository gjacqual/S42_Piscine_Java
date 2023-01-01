package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private final DataSource dataSrc;
    private static final String SQL_QUERY = "SELECT * " +
            "FROM messages " +
            "JOIN users u on u.id = messages.author " +
            "JOIN chat_rooms cr on cr.id = messages.room " +
            "WHERE messages.id=?;";

    public MessagesRepositoryJdbcImpl(DataSource dataSrc) {
        this.dataSrc = dataSrc;
    }

    @Override
    public Optional<Message> findById(Long id) {

        try {
            Connection connection = dataSrc.getConnection();
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

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            Message message = new Message(
                    resSet.getInt("id"),
                    user,
                    chatroom,
                    resSet.getString("text"),
                    LocalDateTime.parse(resSet.getString("date_time"), formatter)
            );
            connection.close();
            return Optional.of(message);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("Database Error", e);
        }
    }
}
