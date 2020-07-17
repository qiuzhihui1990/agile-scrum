package tech.qiuz;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import tech.qiuz.bean.LiftOff;

public class ThreadTest {


  @Test
  public void testRunnable() throws InterruptedException {
    LiftOff launch = new LiftOff(20, Thread.MAX_PRIORITY);
    Thread t = new Thread(launch);
    System.out.println(t.isDaemon());
    t.setDaemon(true);
    t.start();
    System.out.println("main thread end");
  }

  @Test
  public void testThead() throws InterruptedException {
    ExecutorService es = Executors.newCachedThreadPool();

    for (int i = 1; i < 6; i++) {
      es.execute(new LiftOff(10, Thread.MIN_PRIORITY));
      es.execute(new LiftOff(10, Thread.MAX_PRIORITY));
    }
    es.shutdown();
    TimeUnit.MILLISECONDS.sleep(10000);
    System.out.println("Writer LiftOff");
  }

  @Test
  public void testExecutor() {
    ExecutorService es = Executors.newSingleThreadExecutor();
    for (int i = 0; i < 5; i++) {
      es.execute(new LiftOff(10, i));
    }
    es.shutdown();
  }

  @Test
  public void testCallable() throws ExecutionException, InterruptedException {
    Callable<String> call = new Callable<String>() {
      @Override
      public String call() throws Exception {
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        TimeUnit.MICROSECONDS.sleep(10);
        return "call success";
      }
    };

    ExecutorService es = Executors.newCachedThreadPool();
    Future f1 = es.submit(new LiftOff(10, Thread.MIN_PRIORITY));
    System.out.println(f1.get());
    Future<String> f2 = es.submit(call);
    System.out.println("start to check f2 status:" + f2.isDone() + ", " + f2.isCancelled());
    f2.cancel(true);
    System.out.println("start to check f2 status:" + f2.isDone() + ", " + f2.isCancelled());
    Future<String> f3 = es.submit(call);
    int i = 0;
    while (!f3.isDone()) {
      System.out.println(i++ + "start to check f3 status:" + f3.isDone() + ", " + f3.isCancelled());
    }
    System.out.println(f3.get());
    ExecutorService esWithFactory = Executors.newCachedThreadPool(new DemoThreadFactory());
    ExecutorService executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L,
        TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new DemoThreadFactory());
  }

  class DemoThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(final Runnable r) {
      Thread t = new Thread(r);
      t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
      return t;
    }
  }

  class Joiner extends Thread {
    private Thread t;
    public Joiner(Thread r){
      this.t = r;
      start();
    }

    public void run() {
      try {
        t.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(getName() + "join completed");
    }
  }

  @Test
  public void testJoin() {
    Thread t1 = new Thread(new LiftOff(10, 2));
    t1.start();
    Joiner joiner = new Joiner(t1);
    Thread t2 = new Thread(new LiftOff(1000000, 2));
    t2.start();
    Joiner joiner2 = new Joiner(t2);
    t2.interrupt();
  }

  class ExecutionTask implements Runnable {
    public void run() {
      Thread t = Thread.currentThread();
      System.out.println("run() by" + t);
      System.out.println("eh :" + t.getUncaughtExceptionHandler());
      throw new RuntimeException();
    }
  }

  class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(final Thread t, final Throwable e) {
      System.out.println("caught " + e);
    }
  }

  @Test
  public void testUncaughtExceptionHandler() {
    ExecutorService es = Executors.newSingleThreadExecutor(new DemoThreadFactory());
    ExecutionTask t = new ExecutionTask();
    es.execute(t);
    es.shutdown();
    Thread t1 = new Thread(new LiftOff(1 ,4));
    // t1.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
    Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
    System.out.println("end the testUncaughtExceptionHandler");
  }

  public static void main(String[] args) {
    ThreadLocal<String> tl = new ThreadLocal<>();
    tl.set("1");

    ThreadTest tt = new ThreadTest();
    // tt.testRunnable();
    // tt.testThead();
    // tt.testJoin();
    tt.testUncaughtExceptionHandler();
    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("Hello");
        System.out.println(tl.get());
       tl.set(Thread.currentThread().getName());
        System.out.println(tl.get());
      }
    }));

    Runtime.getRuntime().availableProcessors();

  }

  @Test
  public void testLock() throws InterruptedException {
    Lock lock = new ReentrantLock();
    if (lock.tryLock()) {
      System.out.println("locking");
    }
    lock.unlock();

    if(lock.tryLock(10, TimeUnit.MILLISECONDS)) {
      System.out.println("locking");
    }
    lock.unlock();
  }

  class ThreadLocalVariableHolder {
    private  ThreadLocal<Integer> value = new ThreadLocal<Integer>() {
      private Random rand = new Random(47);
      protected synchronized Integer initialValue() {
        return rand.nextInt(100000);
      }
    };
    public  void increment() {
      value.set(value.get() + 1);
    }
  }
}
