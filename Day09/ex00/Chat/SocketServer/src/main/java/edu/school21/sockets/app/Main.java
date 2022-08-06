package edu.school21.sockets.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import edu.school21.sockets.server.Server;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Parameters(separators = "=")
public class Main {
    @Parameter(names = "--port")
    private Integer port;
    public static void main(String[] args) throws IOException {
        if(args.length != 1 || !args[0].startsWith("--port")) {
            System.err.println("Usage: java - jar target/socket-server . jar --port=8081");
            System.exit(1);
        }
        Main main = new Main();
        JCommander.newBuilder().addObject(main).build().parse(args);
        main.runMain();
    }

    private void runMain() throws IOException {
        if (port == null) {
            throw new RuntimeException("Usage: java - jar target/socket-server . jar --port=8081");
        }
        Server server = new Server(port);
        server.runServer();

    }
}