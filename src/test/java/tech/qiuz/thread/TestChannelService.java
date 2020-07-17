package tech.qiuz.thread;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestChannelService {
  public static void main(String[] args) throws IOException {
    CyclicBarrier cyclicBarrier = new CyclicBarrier(1000);
    int i = 1000;
    while (i-- > 0) {
      new Thread(new NewTask(cyclicBarrier)).start();
    }
    while(true) { }
  }

  static class NewTask implements Runnable {
    NewTask(CyclicBarrier c) {
      this.cyclicBarrier = c;
    }
    CyclicBarrier cyclicBarrier;
    @Override
    public void run() {
      try {
        System.out.println(Thread.currentThread() + "before await");
        cyclicBarrier.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }
      try {
        System.out.println(Thread.currentThread() + "before execute");
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
            .get()
            .addHeader("X-Dianrong-App-Id", "borrow-channel")
            .url("http://10.18.19.17:30741/v1/market/channel/code/d_boluodai_a")
            .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(
            Thread.currentThread() + StringUtils.toEncodedString(response.body().bytes(), Charset.defaultCharset()));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
