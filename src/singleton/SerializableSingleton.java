package singleton;

import java.io.*;

/**
 * 不可通过反射，可序列化的线程安全单例模式
 * 从序列化中读取的单例覆盖当前单例
 *
 * @author Unrestraint
 */
public class SerializableSingleton implements Serializable {
    int i=0;
    private static boolean initialized = false;

    //强制不可反射获取
    private SerializableSingleton(){
        synchronized (SerializableSingleton.class){
            if(initialized==false){
                System.out.println("new instance");
                initialized=true;
            }else {
                throw new RuntimeException("禁止创建多个单例！");
            }
        }
    }

    //实现序列化，手动将单例写入流
    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeInt(i);
    }
    //实现反序列化，从流中读取的单例信息覆盖当前单例
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        SerializableSingleton singleton = getInstance();
        singleton.i = s.readInt();
    }

    //替换反序列化返回值
    private Object readResolve(){
        return getInstance();
    }


    public void get(){
        i++;
        System.out.println(i);
    }

    private static class SingletonHolder{
        static SerializableSingleton singleton = new SerializableSingleton();
    }

    public static SerializableSingleton getInstance(){
        return SingletonHolder.singleton;
    }


    //测试
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InterruptedException, IOException, ClassNotFoundException {
        getInstance().get();

        //测试反射,反射无法破坏单例性
        //SerializableSingleton.class.newInstance();

        //测试序列化，输出 1 2 2 表示单例被覆盖
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ObjectOutput out = new ObjectOutputStream(buffer);
        out.writeObject(SerializableSingleton.getInstance());

        SerializableSingleton.getInstance().get();

        InputStream buff = new ByteArrayInputStream(buffer.toByteArray());
        ObjectInput  in = new ObjectInputStream(buff);

        SerializableSingleton serializable = (SerializableSingleton) in.readObject();
        serializable.get();

    }
}
