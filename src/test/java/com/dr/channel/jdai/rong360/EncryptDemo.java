package com.dr.channel.jdai.rong360;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptDemo {
    public static void main(String[] args) throws Exception
    {
        System.out.println("Hello World!");

        String secret = "ZDU2NWEyZGNiZDYw";
        // 分配的
        String source = "rong360dkhhrxxx";
        String needEncryptedData = "{\"mobile\":\"18856568711\",\"callback_key\":\"4c2f3737586263767448455a554439362f6e433478413d3d\"}";
        String random = "74b925c374bc3c45";
        Integer timestamp = 1543747797;

        // 加密
        String encrypted = Base64.getEncoder().encodeToString((encrypt(needEncryptedData, secret, random)));
        System.out.println(encrypted);

        // 签名
        String sign = MD5.GetMD5CodeUtf8(secret + source + encrypted + timestamp + random);
        System.out.println(sign);

    }


    public static byte[] encrypt(String input, String secret, String random) throws Exception {
        try {
            SecretKey secretKey = new SecretKeySpec(secret.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(random.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            return cipher.doFinal(input.getBytes());
        } catch (Exception e) {
            throw e;
        }
    }
}
