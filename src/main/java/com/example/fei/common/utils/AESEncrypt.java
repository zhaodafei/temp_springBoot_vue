package com.example.fei.common.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 用于后端之间数据传输
 */
public class AESEncrypt {
    // 用于封装随机产生的公钥与私钥
    // private static Map<Integer, String> keyMap = new HashMap<Integer, String>();

    public static String bytesToHexString(byte[] sources){
        if (sources == null) return null;
        StringBuilder stringBuffer = new StringBuilder();
        for (byte source : sources) {
            String result = Integer.toHexString(source& 0xff);
            if (result.length() < 2) {
                result = "0" + result;
            }
            stringBuffer.append(result);
        }
        return stringBuffer.toString();
    }

    public static byte[] hexStringToBytes(String hexString){
        if (hexString.length() % 2 != 0) throw new IllegalArgumentException("hexString length not valid");
        int length = hexString.length() / 2;
        byte[] resultBytes = new byte[length];
        for (int index = 0; index < length; index++) {
            String result = hexString.substring(index * 2, index * 2 + 2);
            resultBytes[index] = Integer.valueOf(Integer.parseInt(result, 16)).byteValue();
        }
        return resultBytes;
    }
    // ==================


    /**
     * RSA公钥加密
     *
     * @param str       加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String encrypt(String str, String publicKey)throws Exception{
        // base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey.getBytes());
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        // RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return Base64.encodeBase64String(cipher.doFinal(str.getBytes("utf-8")));
    }

    /**
     * RSA私钥解密
     *
     * @param str        加密字符串
     * @param privateKey 私钥
     * @return 解密值
     * @throws Exception 解密过程中的异常信息
     */
    public static String decrypt(String str, String privateKey) throws Exception{
        // 64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("utf-8"));
        // base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey.getBytes());
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(cipher.doFinal(inputByte));
    }

    /**
     * 加密
     * @param input
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptByAES(String input,String key) throws Exception{
        // 算法
        String algorithm = "AES";
        String transformation = "AES";
        // Cipher：密码，获取加密对象
        // transformation:参数表示使用什么类型加密
        Cipher cipher = Cipher.getInstance(transformation);
        // 指定秘钥规则
        // 第一个参数表示：密钥，key的字节数组 长度必须是16位
        // 第二个参数表示：算法
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), algorithm);
        // 对加密进行初始化
        // 第一个参数：表示模式，有加密模式和解密模式
        // 第二个参数：表示秘钥规则
        cipher.init(Cipher.ENCRYPT_MODE,sks);
        // 进行加密
        byte[] bytes = cipher.doFinal(input.getBytes());

        // base64编码的公钥
        // System.out.println(Base64.encodeBase64String(cipher.doFinal(bytes)));


        return bytesToHexString(bytes);
    }

    /**
     * 解密
     * @param input
     * @param key
     * @return
     * @throws Exception
     */
    public static String decryptByAES(String input,String key)throws Exception{
        // 算法
        String algorithm = "AES";
        String transformation = "AES";
        // Cipher：密码，获取加密对象
        // transformation:参数表示使用什么类型加密
        Cipher cipher = Cipher.getInstance(transformation);
        // 指定秘钥规则
        // 第一个参数表示：密钥，key的字节数组 长度必须是16位
        // 第二个参数表示：算法
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), algorithm);
        // 对加密进行初始化
        // 第一个参数：表示模式，有加密模式和解密模式
        // 第二个参数：表示秘钥规则
        cipher.init(Cipher.DECRYPT_MODE,sks);
        // 进行解密
        byte [] inputBytes = hexStringToBytes(input);
        byte[] bytes = cipher.doFinal(inputBytes);
        return new String(bytes);
    }

    // ======================
    public static void main(String[] args) throws Exception {
        String input = "13600001111";
        String key = "da@fei-1234-keys"; // 必须是16位
        String input2 = encryptByAES(input, key);

        System.out.println("加密:  " + input2);
        System.out.println("解开:  " + decryptByAES(input2, key));

    }
}
