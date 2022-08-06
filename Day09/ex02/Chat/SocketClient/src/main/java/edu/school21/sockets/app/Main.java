package edu.school21.sockets.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import edu.school21.sockets.client.Client;

import java.io.IOException;

@Parameters(separators = "=")
public class Main {

    @Parameter(names = "--server-port")
    private Integer serverPort;

    public static void main(String[] args) throws IOException {
        if(args.length != 1 || !args[0].startsWith("--server-port")) {
            System.err.println("Usage: java - jar target/socket-client . jar --server-port=8081");
            System.exit(1);
        }
        Main main = new Main();
        JCommander.newBuilder().addObject(main).build().parse(args);
        main.runMain();

    }

    private void runMain() throws IOException {
        if (serverPort == null) {
            throw new RuntimeException("Usage: $ java - jar target/socket-client .jar -- server -port=8081");
        }
        Client client = new Client(serverPort);
        client.runClient();

    }
}