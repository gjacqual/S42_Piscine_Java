package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
    public static void main(String[] args) throws SQLException {

        HikariDataSource dataSource = hikariDataSource();

        User creator = new User(1, "max1", "defpassword0", new ArrayList(), new ArrayList());
        User author = creator;

        Chatroom room = new Chatroom(1, "Main", creator, new ArrayList());

        Message message = new Message(null, author, room, "Hello Everybody!", LocalDateTime.now());
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);
        messagesRepository.save(message);
        System.out.print("Message saved: №");
        System.out.println(message.getId());
        System.out.println(message);
    }
}
