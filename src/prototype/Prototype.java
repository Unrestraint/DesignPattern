package prototype;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 原型模式
 * 使用原型模式创建对象比直接new一个对象在性能上要好的多，因为Object类的clone方法是一个本地方法，它直接操作内存中的二进制流，特别是复制大对象时，性能的差别非常明显。
 * 注意深拷贝与浅拷贝
 *
 * String 是只读的
 * @author Unrestraint
 */

public class Prototype implements  Cloneable{
    String id;
    ArrayList list = new ArrayList();
    ArrayList list2 = new ArrayList();

    public Prototype clone(){
        Prototype prototype = null;
        try{
            prototype = (Prototype)super.clone();
            prototype.list2 = (ArrayList) list2.clone();
        }catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
        return prototype;
    }
    @Override
    public String toString(){
        return id;
    }
}

class Main{

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Prototype p = new Prototype();
        p.id = "Unrestraint";
        Prototype clone = p.clone();
        System.out.println(clone==p);
        System.out.println(clone.list==p.list);
        System.out.println(clone.list2==p.list2);
        System.out.println(clone.id);
        System.out.println(clone.id==p.id);


        Method  method = Object.class.getDeclaredMethod("clone",null);
        method.setAccessible(true);
        Arrays.asList(method.invoke(p)).forEach(System.out::println);
    }
}
