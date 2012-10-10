package clientssh;

import java.io.*;
import java.net.*;
import java.util.*;
import java.security.PublicKey;
import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;


public class Client {
    public static void main(String[] args) throws IOException {

        Socket socket = null;//socket de conexiune
        OutputStream out = null;//stream pt output mesaj
        ObjectInputStream in = null;//stream pentru primire obiect tip Key(folosit la criptarea mesajelor)
        String adresa="localhost";//adresa serverului
        int port=1234;//portul serverului
        PublicKey cheie;//cheia folosita la criptare
        criptare cr=new criptare();//initializare criptare
        byte[] mesaj=null;//mesajul criptat

        try {
            socket = new Socket(adresa, port);//socket conexiune server
            System.out.println("Conectat la server: "+adresa+" pe portul: "+port);
            out = socket.getOutputStream();//output
            in = new ObjectInputStream(socket.getInputStream());//input stream pentru cheie
            
            cheie=(PublicKey) in.readObject();//primesc cheia d la server
            System.out.println("Cheie primita:");
            System.out.println(cheie.toString());//afisam cheia primita
            BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));//pentru citirea de la tastatura
            String input;//textul scris la tastatura
            while(cheie!=null){//verifica daca cheia de la server este valabila
            	System.out.print("Introdu mesaj>");
            	input=sysin.readLine();//citeste textul de la tastatura
            	if(input!=null&&!input.equals("exit")){//verificam daca se doreste inchiderea clientului si daca textul este valabil
            		mesaj=cr.cripteaza(cheie,input);//measajul criptat realizat din textul introdus la tastatura
            		System.out.println("Mesaj trimis: "+input+"\nMesaj criptat("+new String(mesaj)+")");
            		out.write(mesaj);//scriem mesajul criptat in OutputStream
            		out.flush();//trimitem mesajul la server

            	}
            	else{
            		mesaj=cr.cripteaza(cheie,input);//criptam mesajul de inchidere conexiune
            		out.write(mesaj);//scriem mesajul criptat in OutputStream
            		out.flush();//trimitem mesajul la server
            		break;
            	}
            	
            }
            out.close();//inchidem OutputStream
            in.close();//inchidem ObjectInputStream
            sysin.close();//inchidem BufferedReader
            socket.close();//inchidem Socket (conexiune)
            
            
     //------------------------------------------------Tratare exceptii       
        } catch (UnknownHostException e) {
            System.err.println("Nu gasesc server: "+adresa +"(verificati adresa introdusa)");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Eroare io server");
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e){
        	System.err.println("Nu s-a gasit clasa");
            System.exit(1);
        } catch (NoSuchAlgorithmException e){
        	System.err.println("Eroare algoritm");
        	System.exit(1);
        } catch (NoSuchPaddingException e){
        	System.err.println("Eroare padding(nu exista)");
        	System.exit(1);
        } catch (InvalidKeyException e){
        	System.err.println("Cheie invalida");
        	System.exit(1);
        } catch (IllegalBlockSizeException e){
        	System.err.println("Eroare block size");
        	System.exit(1);
        } catch (BadPaddingException e){
        	System.err.println("Eroare bad padding");
        	System.exit(1);
        } 
    }
}
