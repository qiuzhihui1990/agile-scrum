package tech.qiuz;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class StringTest {

  @Test
  public void testStringBuilder() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("cc");
    stringBuilder.toString();
  }

  @Test
  public void testFormatter() {
    Formatter formatter = new Formatter(System.out);
    formatter.format("%s asdfd", "like");
    System.out.format("%d is number", 12);
    System.out.println("%s cc");

    String sf = String.format("s: %s, d: %d ", "cc", 12);
    System.out.println(sf);
  }

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new StringReader("I like good thing!"));
    System.out.println(reader.readLine());
    System.out.println(reader.readLine());

    Scanner scanner = new Scanner(System.in);
    String inputString = scanner.nextLine();
    System.out.println(inputString);
    System.out.println(scanner.nextDouble());

    Scanner newScan = new Scanner("12, 42");
    newScan.useDelimiter(",");
    while (newScan.hasNextInt()) {
      System.out.println(newScan.nextInt());
    }
  }

  @Test
  public void testStringTokenizer() {
    String input = "I am qiu xi ki";
    StringTokenizer tokenizer = new StringTokenizer(input);
    while (tokenizer.hasMoreElements()) {
      System.out.println(tokenizer.nextElement());
    }
  }

  @Test
  public void testReg() {
    String str1 = "I like two girls";
    System.out.println(Arrays.toString(str1.split(" ")));
  }
}
