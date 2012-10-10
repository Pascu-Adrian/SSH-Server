/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servers;

import java.io.IOException;
import java.net.ServerSocket;
import java.security.NoSuchAlgorithmException;
import secure.*;

/**
 *
 * @author MindSlave
 */
public class Server {

    ServerSocket serverSocket = null;
    boolean listening = true;
    int port = 1234;
    int numConnections;
    

    public Server() throws IOException {


        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Serverul ruleaza");
        } catch (IOException e) {
            System.err.println("Could not listen on port:" + port);
            System.exit(-1);
        }
        numConnections = 0;
        while (listening) {
            numConnections++;
            new ServerThread(serverSocket.accept()).start();
            
        }

        serverSocket.close();
    }
}
