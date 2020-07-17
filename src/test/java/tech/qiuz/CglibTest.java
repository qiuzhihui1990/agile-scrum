package tech.qiuz;

import org.junit.Test;

import java.lang.reflect.Method;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibTest {

  @Test
  public void test() {
    System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "./code");
    //实例化一个增强器，也就是cglib中的一个class generator
    Enhancer eh = new Enhancer();
    //设置目标类
    eh.setSuperclass(Target.class);
    // 设置拦截对象
    eh.setCallback(new Interceptor());
    // 生成代理类并返回一个实例
    Target t = (Target) eh.create();
    t.f();
    t.g();
  }

  @Test
  public void test2() {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(Target.class);
    enhancer.setCallback(new IterceptorImpl());
    Target t = (Target) enhancer.create();
    t.f();
    t.g();
  }
}

class Target{
  public void f(){
    System.out.println("Target f()");
  }
  public void g(){
    System.out.println("Target g()");
  }
}

class IterceptorImpl implements MethodInterceptor {

  @Override
  public Object intercept(final Object obj, final Method method, final Object[] args, final MethodProxy proxy)
      throws Throwable {
    System.out.println("I am intercept begin");

    proxy.invokeSuper(obj, args);
    System.out.println("I am intercept end");
    return null;
  }
}

class Interceptor implements MethodInterceptor {
  @Override
  public Object intercept(Object obj, Method method, Object[] args,    MethodProxy proxy) throws Throwable {
    System.out.println("I am intercept begin");
//Note: 此处一定要使用proxy的invokeSuper方法来调用目标类的方法
    proxy.invokeSuper(obj, args);
    System.out.println("I am intercept end");
    return null;
  }
}
