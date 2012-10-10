/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servers;

import Protocol.HandshakeProtocol;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.PublicKey;
import secure.GenereazaCheie;
import ssh.Main;

/**
 *
 * @author MindSlave
 */
public class ServerThread extends Thread {

    private Socket socket = null;
    private GenereazaCheie gc = null;

    public ServerThread(Socket socket) {
        super("ServerThread");
        this.socket = socket;

        //TODO apelul la genereazacheie singleton
        gc = Main.gc;
    }

    @Override
    public void run() {
        try {
            //trimit cheia publica  la client
            //pregatesc streamu sa-l trimit la client

            sendPublicKey(socket);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            InputStream in= socket.getInputStream();
            
            HandshakeProtocol hsp = new HandshakeProtocol();
            String  outputLine;
           
            outputLine = "Conectat la server";
            out.println(outputLine);
           System.out.println("Client conectat: "+socket.getInetAddress().toString());
            int aux;
            boolean connect=true;
            while (in != null && connect) {
                 byte[] outputdecrypt=new byte[in.available()];
                 
                while ((in.read(outputdecrypt)) != 0) {
                    //outputLine = "Mesaj primit";
                    //out.println(outputLine);
                    //byte[] decrypt=inputLine.getBytes();
                  //  System.out.println(inputLine);
               //     System.out.println(aux);
                  // System.out.println(hsp.procesInput(outputdecrypt));
                  outputLine=hsp.procesInput(outputdecrypt);
                    //out.print(outputLine);
                    System.out.println("Clientul spune: "+outputLine);
                    if (outputLine.equals("exit")) {
                        connect=false;
                        System.out.println("Client deconectat: "+socket.getInetAddress().toString());
                        break;
                    }
                    
                }
               /*  for(int i=0; i<outputdecrypt.length; i++)
                     System.out.println(outputdecrypt[i]);*/
            }
            out.close();
            in.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendPublicKey(Socket socket) {
        try {

            ObjectOutputStream publicKeySender = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            PublicKey publicK = (PublicKey) gc.getPublicKey();
            publicKeySender.writeObject(publicK);
            publicKeySender.flush();

            // sock2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
