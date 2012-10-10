/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package misc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;

/**
 *
 * @author student
 */
public class LucruChei {
     public static void saveToFile(String fileName,
  BigInteger mod, BigInteger exp) throws IOException {
  ObjectOutputStream oout = new ObjectOutputStream(
    new BufferedOutputStream(new FileOutputStream(fileName)));
  try {
    oout.writeObject(mod);
    oout.writeObject(exp);
  } catch (Exception e) {
    throw new IOException("Unexpected error", e);
  } finally {
    oout.close();
  }
}
     public static PublicKey readKeyFromFile(String keyFileName) throws IOException {
  InputStream in =new FileInputStream(new File(keyFileName));
  ObjectInputStream oin =new ObjectInputStream(new BufferedInputStream(in));
  try {
    BigInteger m = (BigInteger) oin.readObject();
    BigInteger e = (BigInteger) oin.readObject();
    RSAPublicKeySpec keySpec = new RSAPublicKeySpec(m, e);
    KeyFactory fact = KeyFactory.getInstance("RSA");
    PublicKey pubKey = fact.generatePublic(keySpec);
    return pubKey;
  } catch (Exception e) {
    throw new RuntimeException("Spurious serialisation error", e);
  } finally {
    oin.close();
  }
}

}
