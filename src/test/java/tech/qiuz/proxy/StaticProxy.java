package tech.qiuz.proxy;

public class StaticProxy {

  public static void main(String[] args) {
    Hello hp = HelloImpl.getProxyInstance();
    hp.sayHello();
  }
}

interface Hello {
  void sayHello();
}

class HelloImpl implements Hello {
  private HelloImpl() {}

  @Override
  public void sayHello() {
    System.out.println("Hello world!");
  }

  public static Hello getProxyInstance() {

    return new HelloProxy(new HelloImpl());
  }
}

class HelloProxy implements Hello {
  private Hello helloImpl;

  public HelloProxy(Hello hello) {
    this.helloImpl = hello;
  }
  @Override
  public void sayHello() {
    System.out.println("before say hello");
    helloImpl.sayHello();
    System.out.println("after say hello");
  }
}

