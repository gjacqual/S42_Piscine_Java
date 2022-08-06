package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

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
    public static void main(String[] args) {
        HikariDataSource dataSource = hikariDataSource();
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);
        Scanner console = new Scanner(System.in);
        System.out.println("Enter a message ID");
        if (console.hasNextLong()) {
            Long id = console.nextLong();
            Optional<Message> message = messagesRepository.findById(id);
            if (message.isPresent()) {
                System.out.println(message.get());
            } else {
                System.out.println("Message not found");
            }
        } else {
            System.out.println("Error: incorrect input!");
        }
    }
}
