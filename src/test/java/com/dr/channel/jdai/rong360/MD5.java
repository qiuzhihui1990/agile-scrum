package com.dr.channel.jdai.rong360;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

public class MD5 {
    private static final String[] strDigits = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public MD5() {
    }

    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if(bByte < 0) {
            iRet = bByte + 256;
        }

        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    private static String byteToNum(byte bByte) {
        int iRet = bByte;
        System.out.println("iRet1=" + bByte);
        if(bByte < 0) {
            iRet = bByte + 256;
        }

        return String.valueOf(iRet);
    }

    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();

        for(int i = 0; i < bByte.length; ++i) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }

        return sBuffer.toString();
    }

    public static String GetMD5Code(String strObj) {
        String resultString = null;

        try {
            new String(strObj);
            MessageDigest ex = MessageDigest.getInstance("MD5");
            resultString = byteToString(ex.digest(strObj.getBytes()));
        } catch (NoSuchAlgorithmException var3) {
            var3.printStackTrace();
        }

        return resultString;
    }

    public static String GetMD5CodeUtf8(String strObj) {
        String resultString = null;

        try {
            resultString = new String(strObj);
            MessageDigest ex = MessageDigest.getInstance("MD5");

            try {
                byte[] e = strObj.getBytes("utf-8");
                boolean off = false;
                if(e.length >= 3 && e[0] == -17 && e[1] == -69 && e[2] == -65) {
                    byte off1 = 3;
                    e = Arrays.copyOfRange(e, off1, e.length);
                }

                resultString = byteToString(ex.digest(e));
            } catch (UnsupportedEncodingException var5) {
                var5.printStackTrace();
            }
        } catch (NoSuchAlgorithmException var6) {
            var6.printStackTrace();
        }

        return resultString;
    }

    public static String getMd5Value(Map<String, String> paramsMap) {
        StringBuilder value = new StringBuilder(128);
        Object[] key = paramsMap.keySet().toArray();
        Arrays.sort(key);

        for(int i = 0; i < key.length; ++i) {
            if(!((String)paramsMap.get(key[i])).equals("") && !((String)paramsMap.get(key[i])).equals("null") && null != paramsMap.get(key[i])) {
                value.append((String)paramsMap.get(key[i]));
            }
        }

        return GetMD5CodeUtf8(value.toString());
    }
}