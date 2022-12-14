package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class Program {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/java_chat_db?currentSchema=chat";
    private static final String DB_USER = "javauser";
    private static final String DB_PASSWORD = "123123124";

    private static HikariDataSource hikariDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(DB_URL);
        hikariConfig.setUsername(DB_USER);
        hikariConfig.setPassword(DB_PASSWORD);
        hikariConfig.addDataSourceProperty( "cachePrepStmts" , "true" );
        hikariConfig.addDataSourceProperty(  "prepStmtCacheSize" , "250" );
        hikariConfig.addDataSourceProperty(  "prepStmtCacheSqlLimit" , "2048" );

        return new HikariDataSource(hikariConfig);
    }
    public static void main(String[] args) throws SQLException, FileNotFoundException {

        HikariDataSource dataSource = hikariDataSource();
        Connection connection = hikariDataSource().getConnection();
       initData(connection);

        User user = new User(3, "Locke", "4815162342", null, null);
        Chatroom chatRoom = new Chatroom(4, "Room", user, null);

        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);

        Optional<Message> messageOptional = messagesRepository.findById(11L);
        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            message.setAuthor(user);
            message.setText("Bye Bye Beautiful");
            message.setRoom(chatRoom);
            message.setDateTime(null);
            messagesRepository.update(message);
        }

    }

    public static void initData(Connection connection) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(System.getProperty("user.dir") + "/src/main/resources/schema.sql"))
                .useDelimiter(";");
        try {
            while (scanner.hasNext()) {
                connection.createStatement().execute(scanner.next().trim());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        scanner.close();

        Scanner scanner2 = new Scanner(new File(System.getProperty("user.dir") + "/src/main/resources/data.sql"))
                .useDelimiter(";");
        try {
            while (scanner2.hasNext()) {
                connection.createStatement().execute(scanner2.next().trim());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        scanner2.close();
    }

}
