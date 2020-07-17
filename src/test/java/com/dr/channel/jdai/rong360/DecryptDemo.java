package com.dr.channel.jdai.rong360;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;

public class DecryptDemo {
  public static void main(String[] args) throws Exception {
    System.out.println("Hello World!");

    String secret = "ZDU2NWEyZGNiZDYw";
    // 从请求中拿到
    String source = "rong360dkhhr";
    String encrypted =
        "Stu/nft0l+I1Fc5Ec2o5BSM0FXWVUPMmN/G2h+U0hhlOnOdBgDBDrYoCwxJvz/hx"
            + "+vmxfJTdXKRmVyPCR6oNQbO5xmOM2SbcfCOoohrECdZjVdv2MCVpKHwGTPiQ94rz";
    String random = "d95d0b7051610705";
    Integer timestamp = 1543748362;

    // 签名
    String sign = MD5.GetMD5CodeUtf8(secret + source + encrypted + timestamp + random);
    System.out.println(sign);

    String unsignString = "ZDU2NWEyZGNiZDYw{\"timestamp\":\"1543731453\",\"random\":\"1f21843d\","
        + "\"callback_key\":\"4c2f3737586263767448455a554439362f6e433478413d3d\",\"register_date\":\"2019-04-30 "
        + "10:50:11\"}";
    String signString = MD5.GetMD5CodeUtf8(unsignString);
    System.out.println("unsignString:" + unsignString);
    System.out.println("signString:" + signString);

    //解密
    byte decrypted[] = decrypt(encrypted, secret, random);
    System.out.println(new String(decrypted));
  }

  public static byte[] decrypt(String encrypted, String secret, String random) throws Exception {
    try {
      SecretKey secretKey = new SecretKeySpec(secret.getBytes(), "AES");
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      IvParameterSpec iv = new IvParameterSpec(random.getBytes());
      cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
      return cipher.doFinal(Base64.getDecoder().decode(encrypted.getBytes()));
    } catch (Exception e) {
      throw e;
    }
  }

  public static byte[] encrypt(String encrypted, String secret, String random) throws Exception {
    try {
      SecretKey secretKey = new SecretKeySpec(secret.getBytes(), "AES");
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      IvParameterSpec iv = new IvParameterSpec(random.getBytes());
      cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
      return cipher.doFinal(Base64.getDecoder().decode(encrypted.getBytes()));
    } catch (Exception e) {
      throw e;
    }
  }
}
