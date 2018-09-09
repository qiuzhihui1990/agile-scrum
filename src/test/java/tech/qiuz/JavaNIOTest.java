package tech.qiuz;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.channels.Pipe;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

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
}
