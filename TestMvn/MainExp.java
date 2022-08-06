package edu.school21.chat.models;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/java_chat_db";
    private static final String DB_USER = "javauser";
    private static final String DB_PASS = "123123124";
    public static void main(String[] args) {
        try (Connection connect = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            System.out.println("Successful connection to the database.");
//            String sqlCommand0 = "CREATE SCHEMA IF NOT EXISTS chat;";
            Statement statement = connect.createStatement();
//            statement.executeUpdate("DROP SCHEMA IF EXISTS chat CASCADE;");
//            statement.executeUpdate(sqlCommand0);

            Scanner scanner = new Scanner(new File(System.getProperty("user.dir") + "/src/main/resources/schema.sql"))
                    .useDelimiter(";");
            try {
                while (scanner.hasNext()) {
                    connect.createStatement().execute(scanner.next().trim());
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            scanner.close();

            Scanner scanner2 = new Scanner(new File(System.getProperty("user.dir") + "/src/main/resources/data.sql"))
                    .useDelimiter(";");
            try {
                while (scanner2.hasNext()) {
                    connect.createStatement().execute(scanner2.next().trim());
                }
            } catch (SQLException ex2) {
                System.out.println(ex2.getMessage());
            }
            scanner2.close();

        } catch (Exception e) {
            System.out.println("Connection failed!");

            System.out.println(e.getMessage());
        }
    }

}