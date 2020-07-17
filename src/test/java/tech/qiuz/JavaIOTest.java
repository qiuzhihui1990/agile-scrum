package tech.qiuz;

import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.regex.Pattern;

public class JavaIOTest {
  @Test
  public void testFile() {
    File path = new File(".");
    System.out.println(Arrays.toString(path.list()));
    FilenameFilter filenameFilter = new FilenameFilter() {
      @Override
      public boolean accept(final File dir, final String name) {
        Pattern pattern = Pattern.compile("^gradle+$");
        return pattern.matcher(name).matches();
      }
    };

    System.out.println(Arrays.toString(path.list(filenameFilter)));
  }

  @Test
  public void testInputStream() throws IOException {

    ObjectOutputStream os = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream("/Users/qiuzhihui/ls.txt")));
    String object = "11";
    os.writeObject(object);
    os.close();
  }
}
