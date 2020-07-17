package tech.qiuz.thread;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import sun.misc.Unsafe;
import tech.qiuz.bean.LiftOff;

public class UnSafeTest {
  public static void main(String[] args) {
    try {
      //reflectUnsafe(args);
      testPerfrom(args);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void failUnsafe(String[] args) {
    try {
      Unsafe unsafe = Unsafe.getUnsafe();
      LiftOff liftOff = new LiftOff(10, 1);
      long ageOffset = unsafe.objectFieldOffset(LiftOff.class.getField("priority"));
      unsafe.putInt(liftOff, ageOffset, 10);
      System.out.println(liftOff.getCountDown());
      System.out.println(unsafe);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static void reflectUnsafe(String[] args) throws Exception {
    // 通过反射实例化Unsafe
    Field f = Unsafe.class.getDeclaredField("theUnsafe");
    f.setAccessible(true);
    Unsafe unsafe = (Unsafe) f.get(null);

    // 实例化Player
    Player player = (Player) unsafe.allocateInstance(Player.class);
    player.setAge(18);
    player.setName("li lei");
    for (Field field : Player.class.getDeclaredFields()) {
      System.out.println(field.getName() + ":对应的内存偏移地址" + unsafe.objectFieldOffset(field));
    }

    System.out.println("-------------------");
    // unsafe.compareAndSwapInt(arg0, arg1, arg2, arg3)
    // arg0, arg1, arg2, arg3 分别是目标对象实例，目标对象属性偏移量，当前预期值，要设的值

    int ageOffset = 12;
    // 修改内存偏移地址为12的值（age）,返回true,说明通过内存偏移地址修改age的值成功
    System.out.println(unsafe.compareAndSwapInt(player, ageOffset, 18, 20));
    System.out.println("age修改后的值：" + player.getAge());
    System.out.println("-------------------");
    System.out.println(unsafe.compareAndSwapInt(player, ageOffset, 21, 25));
    System.out.println("age未修改成功的值：" + player.getAge());
    System.out.println("-------------------");

    // 修改内存偏移地址为12的值，但是修改后不保证立马能被其他的线程看到。
    unsafe.putOrderedInt(player, 12, 33);
    System.out.println("age修改后的值：" + player.getAge());
    System.out.println("-------------------");

    // 修改内存偏移地址为16的值，volatile修饰，修改能立马对其他线程可见
    unsafe.putObjectVolatile(player, 16, "han mei");
    System.out.println("name修改后的值：" + unsafe.getObjectVolatile(player, 16));
  }

  class Player {
    private int age;
    private String name;

    private Player() {
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getAge() {
      return age;
    }

    public void setAge(int age) {
      this.age = age;
    }
  }

  public static void testPerfrom(String[] args) {
    try {
      Constructor<Unsafe> con = (Constructor<Unsafe>) Class.forName("sun.misc.Unsafe").getDeclaredConstructor();
      con.setAccessible(true);
      LiftOff user = new LiftOff(1, 2);
      Unsafe UNSAFE = con.newInstance(null);
      Field filed = user.getClass().getDeclaredField("countDown");
      long s1 = System.currentTimeMillis();
      for (int i = 0; i < 100000000; i++) {
        user.setCountDown(i);
      }
      System.out.println(System.currentTimeMillis() - s1);
      long ageOffset = UNSAFE.objectFieldOffset(filed);
      long s2 = System.currentTimeMillis();
      for (int i = 0; i < 100000000; i++) {
        UNSAFE.putInt(user, ageOffset, i);
      }
      System.out.println(System.currentTimeMillis() - s2);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
