package com.example.fei.common.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * 用于前后端之间数据交互
 */
public class AESEncryptWeb {

    // 使用AES-128-CBC加密模式，key需要为 16 位,key和iv可以相同！
    private static final String KEY = "da@fei-1234-keys";
    private static final String IV = "da@fei-1234-keys";

    /**
     * 加密方法
     * @param data  要加密的数据
     * @param key 加密key
     * @param iv 加密iv
     * @return 加密的结果
     * @throws Exception 异常
     */
    public static String WebEncryptAES(String data, String key, String iv) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");//"算法/模式/补码方式"NoPadding PkcsPadding
        int blockSize = cipher.getBlockSize();

        byte[] dataBytes = data.getBytes();
        int plaintextLength = dataBytes.length;
        if (plaintextLength % blockSize != 0) {
            plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
        }

        byte[] plaintext = new byte[plaintextLength];
        System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

        SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

        cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
        byte[] encrypted = cipher.doFinal(plaintext);

        return new Base64().encodeToString(encrypted);
    }

    /**
     * 解密方法
     * @param data 要解密的数据
     * @param key  解密key
     * @param iv 解密iv
     * @return 解密的结果
     * @throws Exception 异常
     */
    public static String WebDecryptAES(String data, String key, String iv) throws Exception{
        byte[] encrypted1 = new Base64().decode(data);

        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

        cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

        byte[] original = cipher.doFinal(encrypted1);
        return new String(original);
    }

    // 测试方法
    public static void main(String args[]) throws Exception{
        String test1 = "13600001111";
        String test = new String(test1.getBytes(), StandardCharsets.UTF_8);
        System.out.println("数据：" + test);

        String key = KEY;
        String iv = IV;

        String dataEncrypt = WebEncryptAES(test, key, iv);
        System.out.println("加密：" + dataEncrypt);

        String dataDecrypt = WebDecryptAES(dataEncrypt, key, iv).trim();
        System.out.println("解密：" + dataDecrypt);
    }
}
