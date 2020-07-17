package tech.qiuz;

import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Test {
  @Test
  public void testMD5() {
    String  ssnPhone = "13295436155150522194511175619";
    String phone = "1329543615";
    String ssn = "5150522194511175619";
    String md5Identity = crypt(phone);
    System.out.println(md5Identity);
  }

  public static String crypt(String str) {
    if (str == null || str.length() == 0) {
      throw new IllegalArgumentException("String to encript cannot be null or zero length");
    }
    StringBuffer hexString = new StringBuffer();
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(str.getBytes());
      byte[] hash = md.digest();
      for (int i = 0; i < hash.length; i++) {
        if ((0xff & hash[i]) < 0x10) {
          hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
        } else {
          hexString.append(Integer.toHexString(0xFF & hash[i]));
        }
      }
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return hexString.toString();
  }

}
