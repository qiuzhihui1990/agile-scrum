package tech.qiuz.proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ProxyTest {
  @Test
  public void testNoProxy() {
    SampleInterface i = new SampleImpl();
    i.methodA("Cat");
  }

  @Test
  public void testDymicProxy() {
    SampleInterface proxyClass = (SampleInterface) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{SampleInterface.class},
        new SayHelloInvokeHandler(new SampleImpl()));
    System.out.println(proxyClass.getClass().getName());
    proxyClass.methodA("Tom");
  }


  @Test
  public void testGenerateProxyClass() {
    ProxyGenerate.writeProxyClassToHardDisk("/Users/qiuzhihui/$Proxy11.class");
  }
}

interface SampleInterface {
  void methodA(String name);
}

class SampleImpl implements SampleInterface {

  @Override
  public void methodA(final String name) {
    System.out.println("This is " + SampleImpl.class.getName() + "say hello to " + name);
  }
}

class SayHelloInvokeHandler implements InvocationHandler {
  private Object target;

  public SayHelloInvokeHandler(Object target) {
    this.target = target;
  }

  @Override
  public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
    System.out.println("proxy:" + proxy.getClass().getName());
    System.out.println("method:" + method.getName());
    System.out.println("args: " + Arrays.toString(args));

    System.out.println("say hello before do something");
    Object cc = method.invoke(target, args);
    System.out.println("say hello after do something");
    return cc;
  }
}

