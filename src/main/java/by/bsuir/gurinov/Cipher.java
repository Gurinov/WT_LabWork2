package by.bsuir.gurinov;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Cipher {

    private final static class MySecretKey implements SecretKey {

        private byte[] key = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};
        public String getAlgorithm() {
            return "DES";
        }

        public String getFormat() {
            return "RAW";
        }

        public byte[] getEncoded() {
            return key;
        }
    }

    private static SecretKey key;

    private static javax.crypto.Cipher ecipher;
    private static javax.crypto.Cipher dcipher;

    static {
        try {
            key = new MySecretKey();
            ecipher = javax.crypto.Cipher.getInstance("DES");
            dcipher = javax.crypto.Cipher.getInstance("DES");
            ecipher.init(javax.crypto.Cipher.ENCRYPT_MODE, key);
            dcipher.init(javax.crypto.Cipher.DECRYPT_MODE, key);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Cipher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Cipher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Cipher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * Функция шифрования
     * @param str строка открытого текста
     * @return зашифрованная строка в формате Base64
     */
    public static String encrypt(String str) {
        try {
            byte[] utf8 = str.getBytes("UTF8");
            byte[] enc = ecipher.doFinal(utf8);
            return new sun.misc.BASE64Encoder().encode(enc);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Cipher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Cipher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Cipher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Функция расшифрования
     * @param str зашифрованная строка в формате Base64
     * @return расшифрованная строка
     */
    public static String decrypt(String str)  {
        try {
            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
            byte[] utf8 = dcipher.doFinal(dec);
            return new String(utf8, "UTF8");
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Cipher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Cipher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cipher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
