package com.example.fei.common.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class OtherUtils {
    private static String bytesToHexString(byte[] bytes) {
        // http://stackoverflow.com/questions/332079
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(0xFF & aByte);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public static String getHashStr(String str) throws NoSuchAlgorithmException {
        // String str = "fei";
        // MessageDigest mDigest  = MessageDigest.getInstance("SHA");
        MessageDigest mDigest  = MessageDigest.getInstance("MD5");
        mDigest.update(str.getBytes());
        // System.out.println(new BigInteger(mDigest.digest()));
        // System.out.println(bytesToHexString(mDigest.digest()));

        // String cacheKey = String.valueOf(str.hashCode());
        // System.out.println(cacheKey);

        return bytesToHexString(mDigest.digest());
    }
}
