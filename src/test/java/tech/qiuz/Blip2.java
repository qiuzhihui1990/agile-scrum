package tech.qiuz;

import javax.persistence.Transient;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Blip2 implements Externalizable {
  private int i;
  private String s;

  public Blip2() {

  }


  public Blip2(int i, String s) {
    this.i = i;
    this.s = s;
  }
  @Override
  public void writeExternal(final ObjectOutput out) throws IOException {
    out.writeInt(i);
    out.writeObject(s);
    System.out.println("writeExternal ");
  }

  @Override
  public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
    this.i=in.readInt();
    this.s = (String)in.readObject();
    System.out.println("readExternal ");

  }

  @Override
  public String toString() {
    return i + s;
  }
}

class Blip implements Serializable {
  private transient int i;
  private String s;

  public Blip(int i, String s) {
    this.i = i;
    this.s = s;
  }

  @Override
  public String toString() {
    return i + s;
  }

  private void writeObject(ObjectOutputStream oos) throws IOException {
    oos.defaultWriteObject();
    oos.writeInt(i);
    System.out.println("writeObject");
  }

  private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
    ois.defaultReadObject();
    this.i = ois.readInt();
    System.out.println("readObject");

  }
}
