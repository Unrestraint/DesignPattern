package singleton;

import java.io.*;

/**
 * 枚举类实现单例,线程安全
 * 不会因为反射遭到破坏
 * 不会因为反序列化产生新的对象,但通过源码可以发现，如果序列化类是枚举类，那么只会将枚举对象的名字写到流里，反序列化根据名字获取枚举对象
 * 并且先判断是否是枚举类，再判断时候实现序列化，所以枚举类使用序列化的方式进行保存不可行。
 *
 * @author Unrestraint
 */
public enum EnumSingleton implements Serializable{
    INSTANCE;
    public int i=0;
    public String get(){
        i++;
        return "test"+i;
    }
    public static EnumSingleton getInstance(){
        return INSTANCE;
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        System.out.println(1);
        s.defaultWriteObject();
        s.writeInt(i);
    }
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        i = s.readInt();
    }
}

class Test2{
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, IOException, ClassNotFoundException {
        System.out.println(EnumSingleton.getInstance().get());

        //测试反射破坏 java.lang.NoSuchMethodException: singleton.EnumSingleton.<init>()
        //EnumSingleton.class.newInstance().get();

        //测试序列化
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ObjectOutput out = new ObjectOutputStream(buffer);
        out.writeObject(EnumSingleton.getInstance());

        InputStream buff = new ByteArrayInputStream(buffer.toByteArray());
        ObjectInput  in = new ObjectInputStream(buff);

        EnumSingleton serializable= (EnumSingleton) in.readObject();
        System.out.println(serializable.get());
        System.out.println(serializable==EnumSingleton.getInstance());

    }
}
