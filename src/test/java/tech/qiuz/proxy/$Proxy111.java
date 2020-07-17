package tech.qiuz.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

public class $Proxy111 extends Proxy implements  SampleInterface{
  private static Method m1;
  private static Method m3;
  private static Method m0;
  private static Method m2;

  static
  {
    try
    {
      m1 = Class.forName("java.lang.Object").getMethod("equals", new Class[] {Class.forName("java.lang.Object")});
      m3 = Class.forName("tech.qiuz.proxy.SampleInterface").getMethod("methodA", new Class[0]);
      m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
      m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);
    }
    catch(NoSuchMethodException nosuchmethodexception)
    {
      throw new NoSuchMethodError(nosuchmethodexception.getMessage());
    }
    catch(ClassNotFoundException classnotfoundexception)
    {
      throw new NoClassDefFoundError(classnotfoundexception.getMessage());
    }
  }

  public $Proxy111(InvocationHandler invocationHandler) {
    super(invocationHandler);
  }

  @Override
  public void methodA(final String str) {
    try {
      super.h.invoke(this, m3, new Object[] {str});
    } catch (Throwable throwable) {
      throw new UndeclaredThrowableException(throwable);
    }
  }
}
