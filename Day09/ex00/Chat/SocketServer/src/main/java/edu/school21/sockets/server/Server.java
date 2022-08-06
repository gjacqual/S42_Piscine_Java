package edu.school21.sockets.server;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.services.UsersService;
import edu.school21.sockets.services.UsersServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.sql.SQLOutput;


public class Server {

    private final UsersService usersService;
    private final Integer port;

    public Server(Integer port) {
        this.port = port;
        usersService = new AnnotationConfigApplicationContext(
                SocketsApplicationConfig.class).getBean(UsersServiceImpl.class);
    }

    public void runServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server running...");
        try (Socket clientSocket = serverSocket.accept()) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            bufferedWriter.write("Hello from Server!\n");
            bufferedWriter.flush();
            String line = bufferedReader.readLine();
            if (line.equals("signUp")) {
                bufferedWriter.write("Enter username:\n");
                bufferedWriter.flush();
                do {
                    line = bufferedReader.readLine();
                } while (line.isEmpty());
                String username = line;
                bufferedWriter.write("Enter password:\n");
                bufferedWriter.flush();
                do {
                    line = bufferedReader.readLine();
                } while (line.isEmpty());
                String password = line;
                bufferedWriter.write("Successful!\n");
                bufferedWriter.flush();
                usersService.singUp(username, password);
            }
        } catch (IOException | SQLException e) {
            System.out.println("Error: client");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        serverSocket.close();
        System.out.println("Server stopped!!!");
    }

}
