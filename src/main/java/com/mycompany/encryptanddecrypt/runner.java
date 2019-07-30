
package com.mycompany.encryptanddecrypt;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class runner {

   
    private static String AES="AES";
    private static String key = "!@#$%^&*()_+";
    
    private static String data = "fammoko";
    public static void main(String[] args) {
        try {
            String out=encryptData(data,key);
            System.err.println(out);
            System.err.println(decryptData(out, key));
            
        } catch (Exception ex) {
            Logger.getLogger(runner.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private static String encryptData(String data,String key) throws Exception{
        SecretKeySpec secretkey =generateKey(key);
        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.ENCRYPT_MODE, secretkey);
        byte [] encVal = c.doFinal(data.getBytes());
        String encryptVal = Base64.encode(encVal);
        return encryptVal;
    }
    private static String decryptData(String dataEncypt,String key) throws Exception{
        SecretKeySpec secretkey =generateKey(key);
        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.DECRYPT_MODE, secretkey);
        byte [] decodeVal = Base64.decode(dataEncypt);
        byte [] decVal = c.doFinal(decodeVal);
        String decryptVal = new String(decVal);
        return decryptVal;
    }
    
    private static SecretKeySpec generateKey(String key) throws Exception{
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte [] bytes = key.getBytes("UTF-8");
        digest.update(bytes,0,bytes.length);
        byte [] secretkey = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretkey, AES);
        return secretKeySpec;
    }
    
}
