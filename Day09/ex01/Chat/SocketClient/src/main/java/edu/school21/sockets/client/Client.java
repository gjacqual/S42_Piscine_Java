package edu.school21.sockets.client;

import org.omg.PortableInterceptor.SUCCESSFUL;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private final Integer serverPort;
    private static final String SUCCESS = "Successful!";
    public Client(Integer serverPort) {
        this.serverPort = serverPort;
    }

    public void runClient() {
        try (Socket clientSocket = new Socket("127.0.0.1", serverPort)) {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Scanner console = new Scanner(System.in);
            try {
                while (true) {
                    String serverRespond = bufferedReader.readLine();
                    if (serverRespond == null || serverRespond .startsWith("Error:") ) {
                        break;
                    }
                    System.out.println(serverRespond);
                    if (serverRespond.equalsIgnoreCase("Exit")) {
                        break;
                    }
                    bufferedWriter.write(console.nextLine() + '\n');
                    bufferedWriter.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 }
