package tech.qiuz;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.Pipe;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.SortedMap;
import java.util.prefs.Preferences;

public class JavaNIOTest {
  @Test
  public void testSelector() throws IOException {
    RandomAccessFile file = new RandomAccessFile("data", "rw");
    FileChannel channel = file.getChannel();

    CharBuffer buffer = CharBuffer.allocate(1024);
    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    channel.read(byteBuffer);
    channel.write(byteBuffer);

    SocketChannel socketChannel = SocketChannel.open();
    Selector selector = Selector.open();
    socketChannel.configureBlocking(false);
    SelectionKey key = socketChannel.register(selector, SelectionKey.OP_READ);

    selector.select();

    Pipe pipe = Pipe.open();
    Pipe.SinkChannel sinkChannel = pipe.sink();
  }

  @Test
  public void testChannel() throws IOException {
    FileChannel fc = new FileOutputStream("/users/qiuzhihui/testInput").getChannel();
    fc.write(ByteBuffer.wrap("Hello world!/n".getBytes("UTF-16BE")));
    fc.close();

    fc = new RandomAccessFile("/users/qiuzhihui/testInput", "rw").getChannel();
    fc.position(fc.size());
    fc.write(ByteBuffer.wrap("Some more".getBytes("UTF-16BE")));

    ByteBuffer bufferDirect = ByteBuffer.allocateDirect(1024);
    fc.position(1);
    fc.read(bufferDirect);
    bufferDirect.flip();
    String encoding = System.getProperty("file.encoding");
    System.out.println("Decode using : " + encoding);
    System.out.println("bufferDirect : " + bufferDirect.limit() + bufferDirect.asCharBuffer());
    bufferDirect.flip();
    System.out.println(Charset.forName(encoding).decode(bufferDirect));

    FileChannel fileChannel = new FileInputStream(new File("/users/qiuzhihui/test1")).getChannel();
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    fileChannel.read(buffer);
    buffer.flip();
    System.out.println(buffer.asCharBuffer());
    while (buffer.hasRemaining()) {
      System.out.print((char) buffer.get());
    }
    fileChannel.close();
  }

  @Test
  public void testTransform() throws IOException {
    FileChannel in = new FileInputStream("/users/qiuzhihui/test1").getChannel();
    String encoding = System.getProperty("file.encoding");
    System.out.println("Decode using : " + encoding);
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    in.read(buffer);
    buffer.flip();
    System.out.println(buffer.asCharBuffer());
    FileChannel out = new FileOutputStream("/users/qiuzhihui/test2").getChannel();
    FileLock fl =  out.tryLock();
    if (Objects.nonNull(fl)) {
      in.transferTo(0, in.size(), out);
    }
    fl.release();
    out.close();
    in.tryLock(0, 12, false);
    // out.transferFrom(in, 0, in.size());
  }

  @Test
  public void testDecode() throws IOException {
    FileChannel fc = new RandomAccessFile("/users/qiuzhihui/testDencode", "rw").getChannel();
    System.out.println("fc size: " + fc.size());
    fc.position(fc.size());
    fc.write(ByteBuffer.wrap("I love the world".getBytes("UTF-16BE")));
    fc.close();
    fc = new FileInputStream("/users/qiuzhihui/testDencode").getChannel();
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    fc.read(buffer);
    buffer.flip();
    buffer.rewind();
    System.out.println("end:" + buffer.asCharBuffer());
  }

  @Test
  public void testCharsets() {
    SortedMap<String,Charset> sm = Charset.availableCharsets();
    Iterator<String> iterator = sm.keySet().iterator();
    while (iterator.hasNext()) {
      String name = iterator.next();
      System.out.print(name);
      System.out.println(sm.get("UTF-8").aliases());
    }

  }

  @Test
  public void testViewBuffer() {
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    buffer.asIntBuffer().put(2);
    System.out.println(buffer.asIntBuffer().get());
    buffer.rewind();
    System.out.println("After rewind : " + buffer.asIntBuffer().get());
    buffer.asIntBuffer().put(new int[] {1, 2, 3, 4});
    System.out.println(buffer.getInt());
    buffer.rewind();
    buffer.asCharBuffer().put('c');
    System.out.println(buffer.getChar());

    ByteBuffer bb = ByteBuffer.wrap(new byte[] {9,0,0,0,0,0,'a'});
    bb.rewind();
    while (bb.hasRemaining()) {
      System.out.print(bb.position() + "->" + bb.get() + ",");
    }
    System.out.println("");

    bb.rewind();
    bb = ByteBuffer.wrap(new byte[20]);
    bb.asCharBuffer().put("abcde");
    System.out.println(Arrays.toString(bb.array()));
    bb.rewind();
    bb.order(ByteOrder.BIG_ENDIAN);
    bb.asCharBuffer().put("abcde");
    System.out.println(Arrays.toString(bb.array()));
    bb.rewind();
    bb.order(ByteOrder.LITTLE_ENDIAN);
    bb.asCharBuffer().put("abcde");
    System.out.println(Arrays.toString(bb.array()));
  }

  @Test
  public void testMappedByteBuffer() throws IOException {
    final int length = 0x8FFFFFF;
    MappedByteBuffer mbb = new RandomAccessFile("/users/qiuzhihui/testMapp", "rw")
        .getChannel().map(FileChannel.MapMode.READ_WRITE, 0, length);
    mbb.put("HelloWrold".getBytes());
  }

  @Test
  public void testObjectOutputStream() throws IOException, ClassNotFoundException {
    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/users/qiuzhihui/testSeriz"));
    oos.writeChars("Start:");
    oos.writeInt(111);
    oos.writeObject(new String("cc"));
    oos.close();

    ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/users/qiuzhihui/testSeriz"));
    int i = 0;
    while (i++ < 6) {
      System.out.print(ois.readChar());
    }

    System.out.println(ois.readInt());
    String st = (String) ois.readObject();
    System.out.println(st);
    ois.close();
  }

  @Test
  public void testExternalizable() throws IOException, ClassNotFoundException {
    ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new FileOutputStream("/users/qiuzhihui/testExter2"));;
    objectOutputStream2.writeObject(new Blip2(1, "c"));

    ObjectInputStream ois2  = new ObjectInputStream(new FileInputStream("/users/qiuzhihui/testExter2"));
    Blip2 blip2 = (Blip2) ois2.readObject();
    System.out.println(blip2.toString());
  }

  @Test
  public void testTransient() throws IOException, ClassNotFoundException {
    ByteArrayOutputStream buf = new ByteArrayOutputStream();
    ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(buf);;
    objectOutputStream2.writeObject(new Blip(1, "c"));

    ObjectInputStream ois2  = new ObjectInputStream(new ByteArrayInputStream(buf.toByteArray()));
    Blip blip2 = (Blip) ois2.readObject();
    System.out.println(blip2.toString());
  }

  @Test
  public void testPrefers() {
    Preferences prefs = Preferences.userNodeForPackage(this.getClass());
    prefs.put("keys", "xx");
    prefs.putDouble("keys2", 1.22);
    System.out.println(prefs.get("keys", "yy"));
    System.out.println(prefs.getDouble("keys2", 1.33));
  }

  @Test
  public void testSelector1() {
    try {
      Selector selector = Selector.open();
      // ServerSocketChannel ssc = new ServerSocket(1000).getChannel();
      ServerSocketChannel ssc = ServerSocketChannel.open();
      ssc.bind(new InetSocketAddress(1000));
      ssc.configureBlocking(false);
      ssc.register(selector, SelectionKey.OP_CONNECT);

      if (selector.selectNow() > 0) {
        Iterator<SelectionKey> it = selector.selectedKeys().iterator();
        while (it.hasNext()) {
          SelectionKey key = it.next();
        if (key.isAcceptable()) {
          ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
          SocketChannel socketChannel = serverSocketChannel.accept();
          socketChannel.configureBlocking(false);
          socketChannel.register(selector, SelectionKey.OP_READ);
        } else if (key.isReadable()) {
          SocketChannel socketChannel = (SocketChannel) key.channel();
          ByteBuffer buffer = ByteBuffer.allocate(200);
          int count = socketChannel.read(buffer);
          if (count == -1) {
            socketChannel.close();
            key.cancel();
          }
        } else if (key.isWritable()) {

        } else if (key.isConnectable()) {

        }
      }
    }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
