package tech.qiuz;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {
  @Test
  public void testClassLoader() throws Exception {
    ClassLoader myLoader = new ClassLoader() {
      @Override
      public Class<?> loadClass(final String name) throws ClassNotFoundException {
        try {
          String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
          InputStream is = getClass().getResourceAsStream(fileName);
          if (is == null) {
            return super.loadClass(name);
          } else {
            byte[] b = new byte[is.available()];
            is.read(b);
            return defineClass(name, b, 0, b.length);
          }
        } catch (IOException e) {
          throw new ClassNotFoundException();
        }
      }
    };

    Object obj = myLoader.loadClass("tech.qiuz.ClassLoaderTest").newInstance();
    System.out.println(obj.getClass());
    System.out.println(obj instanceof tech.qiuz.ClassLoaderTest);

  }

  @Test
  public void testClass() throws IllegalAccessException, InstantiationException {
    String str = "ccc";
    String str2 = String.class.newInstance();
    System.out.println(str2);
    System.out.println(str.getClass());
    Class clazz = ClassLoaderTest.class;
    System.out.println(clazz);
    System.out.println(clazz.getConstructors());

    System.out.println(String.class.getClassLoader());
  }
}
