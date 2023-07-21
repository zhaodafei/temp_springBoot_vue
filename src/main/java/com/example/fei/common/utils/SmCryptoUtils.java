package com.example.fei.common.utils;

import com.antherd.smcrypto.sm2.Keypair;
import com.antherd.smcrypto.sm2.Sm2;

import java.util.HashMap;

/**
 * 用于前后端之间数据交互
 */
public class SmCryptoUtils {

    // 随机生成一组公钥和私钥
    private static final String publicKey = "047b8a760757c22985b321744639c2331e813ea2e641e59b02e889cf4a2ab783dd9a658f4b329389fe62727e816c4eeadd740068a53e1b363a4f656b7a6e75b2f1";
    private static final String privateKey = "79d37982c4ca218ac14237dae7d0e41ef6b8555cf50888df8a0fb29a321bcebe";

    /**
     * 随机生成一组公钥和私钥
     */
    public static HashMap<String, String> GetGenerateKey() {
        Keypair keypair = Sm2.generateKeyPairHex();
        String publicKey = keypair.getPublicKey(); //  公钥
        String privateKey = keypair.getPrivateKey(); // 私钥

        return new HashMap<String, String>() {{
            put("publicKey",publicKey);
            put("privateKey",privateKey);
        }};
    }

    // 加密
    public static String WebEncryptSM2(String msg,String publicKey) {
        // cipherMode 1 - C1C3C2，0 - C1C2C3，默认为1
        return Sm2.doEncrypt(msg, publicKey); // 加密结果
    }

    // 解密
    public static String WebDecryptSM2(String encryptData,String privateKey) {
        // cipherMode 1 - C1C3C2，0 - C1C2C3，默认为1
        return  Sm2.doDecrypt(encryptData, privateKey); // 解密结果
    }

    public static void main(String[] args) {
        // HashMap<String, String> pKey = GetGenerateKey();
        // System.out.println("公钥" + pKey.get("publicKey"));
        // System.out.println("私钥" + pKey.get("privateKey"));


        String test1 = "13600001111_fei_飞";
        String dataEncrypt = WebEncryptSM2(test1, publicKey);
        System.out.println("加密：" + dataEncrypt);

        String dataDecrypt = WebDecryptSM2(dataEncrypt, privateKey);
        System.out.println("解密：" + dataDecrypt);
    }
}
