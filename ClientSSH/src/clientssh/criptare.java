/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clientssh;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class criptare {

    public criptare() {


    }
    //--------------------------------------------metoda de criptare
public byte[] cripteaza(PublicKey key, String string) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
      byte[]           input = string.getBytes();//transformarea String-ului in byte[] pentru criptare
        Cipher           cipher = Cipher.getInstance("RSA");//initializare Cipher cu algoritmul RSA
        cipher.init(Cipher.ENCRYPT_MODE, key);//setare Cipher pe modul ENCRYPT (criptare)
        byte[] cipherText=cipher.doFinal(input);//realizarea criptarii
  return cipherText;//returnarea mesajului criptate sub forma byte[]
}

	//--------------------------------------------metoda de decriptare
public String decripteaza(PrivateKey key, byte[] input) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        Cipher           cipher = Cipher.getInstance("RSA");//initializare Cipher cu algoritmul RSA
        cipher.init(Cipher.DECRYPT_MODE, key);//setare Cipher pe modul DECRYPT (decriptare)
        byte[] plainText=cipher.doFinal(input);//realizarea decriptarii
  return new String(plainText);//returnarea mesajului decriptat sub forma String
}

}
