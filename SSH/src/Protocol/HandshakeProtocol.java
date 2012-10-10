/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Protocol;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import secure.GenereazaCheie;
import secure.criptare;
import ssh.Main;

/**
 *
 * @author Dada
 */
public class HandshakeProtocol
{
     private static final int WAITING = 0;
    private static final int KEYPREPARED = 1;
   private int state=WAITING;
   public String procesInput(byte[] theInput)
   {
       PrivateKey privateKey=(PrivateKey) Main.gc.getPrivateKey();
       criptare c=new criptare();
       String theOutput="";
       if(state==WAITING)
       {
           theOutput="S-au pregatit cheile";
           state=KEYPREPARED;

       }
       if(state==KEYPREPARED)
       {
            try {
                theOutput = c.decripteaza(privateKey, theInput);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(HandshakeProtocol.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchPaddingException ex) {
                Logger.getLogger(HandshakeProtocol.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeyException ex) {
                Logger.getLogger(HandshakeProtocol.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalBlockSizeException ex) {
                Logger.getLogger(HandshakeProtocol.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadPaddingException ex) {
                Logger.getLogger(HandshakeProtocol.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
       return theOutput;
   }

}
