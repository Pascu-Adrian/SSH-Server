/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package secure;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MindSlave
 */
public class GenereazaCheie {
Key publicKey;
Key privateKey;
KeyPairGenerator kpg;

    public GenereazaCheie(int biti)  {
        try {
            kpg = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(GenereazaCheie.class.getName()).log(Level.SEVERE, null, ex);
        }
        kpg.initialize(1024);
        KeyPair kp = kpg.genKeyPair();
        publicKey = kp.getPublic();
        privateKey = kp.getPrivate();
    }
    public Key getPrivateKey() {
        return privateKey;
    }

    public Key getPublicKey() {
        return publicKey;
    }




}
