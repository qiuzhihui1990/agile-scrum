package tech.qiuz;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaCoreTest {
  @Test
  public void testE() {
    float expFloat = 1.39E-43f;
    System.out.println(expFloat);
    System.out.println(expFloat * 10);
    float normalFloat = 1.34444444555555555555f;
    System.out.println(normalFloat);
    System.out.println(Math.E);
    double xDouble = 10.0 / 3;
    System.out.println(xDouble);
  }

  @Test
  public void testRound() {
    double above = 0.7;
    double below = 0.4;
    float fabove = 0.7f;
    float fbelow = 0.4f;

    System.out.println((int) above);
    System.out.println((int) below);
    System.out.println((int) fabove);
    System.out.println((int) fbelow);

    System.out.println(Math.round(above));
    System.out.println(Math.round(below));
    System.out.println(Math.round(fabove));
    System.out.println(Math.round(fbelow));
  }

  @Test
  public void testException() {
    try {
      throw new RuntimeException("cc");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testDoubleAndFloat() {
    System.out.println(1.03 - 0.42);
    System.out.println(1.00 - 9 * 0.1);
    System.out.println(BigDecimal.valueOf(1.00).subtract(BigDecimal.valueOf(9 * 0.1)).round(MathContext.DECIMAL32));
  }

  @Test
  public void testPeek() {
    Stream.of("one", "two", "three", "four").peek(e -> {
      e = e + "c";
      System.out.println("c：" + e);
    }).forEach(System.out::println);
  }

  @Test
  public void testBoxing() {
    Integer i = 3;
    int j = i;

  }
}
