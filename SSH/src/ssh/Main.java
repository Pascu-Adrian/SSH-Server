/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ssh;

import Servers.Server;
import java.io.IOException;
import secure.GenereazaCheie;

/**
 *
 * @author Revan
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static GenereazaCheie gc=new GenereazaCheie(1024);
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Server server=new Server();

    }

}
