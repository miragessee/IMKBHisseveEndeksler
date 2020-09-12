package com.hakan.imkbhisseveendeksler.base;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import android.util.Base64;

/**
 * @version 1.0
 * Clase que contiene los métodos encrypt y descrypt, cuyos objetivos son
 * encriptar y desencriptar respectivamente, utilizando los algoritmos y codificación
 * definidas en las variables estáticas alg y cI.
 * Requiere la librería Apache Commons Codec
 * @see <a href="http://commons.apache.org/proper/commons-codec/">Apache Commons Codec</a>
 * @see <a href="http://docs.oracle.com/javase/8/docs/api/javax/crypto/Cipher.html">javax.crypto Class Cipher</a>
 * @see <a href="http://es.wikipedia.org/wiki/Advanced_Encryption_Standard">WikiES: Advanced Encryption Standard</a>
 * @see <a href="http://es.wikipedia.org/wiki/Criptograf%C3%ADa">WikiES: Criptografía</a>
 * @see <a href="http://es.wikipedia.org/wiki/Vector_de_inicializaci%C3%B3n">WikiES: Vector de inicialización</a>
 * @see <a href="http://es.wikipedia.org/wiki/Cifrado_por_bloques">WikiES: Cifrado por bloques</a>
 * @see <a href="http://www.linkedin.com/in/jchinchilla">Julio Chinchilla</a>
 * @author Julio Chinchilla
 */
public class StringEncrypt {

    // Definición del tipo de algoritmo a utilizar (AES, DES, RSA)
    private final static String alg = "AES";
    // Definición del modo de cifrado a utilizar
    private final static String cI = "AES/CBC/PKCS7Padding";

    /**
     * Función de tipo String que recibe una llave (key), un vector de inicialización (iv)
     * y el texto que se desea cifrar
     * @param key la llave en tipo String a utilizar
     * @param iv el vector de inicialización a utilizar
     * @param cleartext el texto sin cifrar a encriptar
     * @return el texto cifrado en modo String
     * @throws Exception puede devolver excepciones de los siguientes tipos: NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException
     */
    public static String encrypt(String key, String iv, String cleartext) throws Exception {
        Cipher cipher = Cipher.getInstance(cI);
        SecretKeySpec skeySpec = new SecretKeySpec(Base64.decode(key, Base64.DEFAULT), alg);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(Base64.decode(iv, Base64.DEFAULT));
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
        byte[] encrypted = cipher.doFinal(cleartext.getBytes());
        return new String(Base64.encode(encrypted, Base64.DEFAULT));
    }

    /**
     * Función de tipo String que recibe una llave (key), un vector de inicialización (iv)
     * y el texto que se desea descifrar
     * @param key la llave en tipo String a utilizar
     * @param iv el vector de inicialización a utilizar
     * @param encrypted el texto cifrado en modo String
     * @return el texto desencriptado en modo String
     * @throws Exception puede devolver excepciones de los siguientes tipos: NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException
     */
    public static String decrypt(String key, String iv, String encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance(cI);
        SecretKeySpec skeySpec = new SecretKeySpec(Base64.decode(key, Base64.DEFAULT), alg);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(Base64.decode(iv, Base64.DEFAULT));
        byte[] enc = Base64.decode(encrypted, Base64.DEFAULT);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
        byte[] decrypted = cipher.doFinal(enc);
        //return new String(Base64.encode(decrypted, Base64.DEFAULT));
        return new String(decrypted);
    }

}
