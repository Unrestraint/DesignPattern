package flyweight;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 享元模式
 * 享元模式是一种对象共享模式，在java中String，Integer,Lang等都用到了享元模式
 * 所以在数字较小的情况下可以使用valueOf生成对象，节约内存。
 *
 * web系统中，我们常常需要对前端返回错误信息，这时候享元模式就是一个非常实用的设计模式
 * 简单写一个实例，实际更复杂
 * @author Unrestraint
 *
 */

class Result{
    int status=0;
    String msg="SUCCESS";
    public Result(){}

    public Result(Exception e){//包含错误信息的结果,实例
        status = 1;
        msg = e.toString();
    }
    @Override
    public String toString(){
        return "{ status:"+status+", msg:"+msg+" }";
    }
}

//享元模式
class ResultFlyweight{
    private static Map<Class,Result> map = new ConcurrentHashMap<>();
    static {
        map.put(Result.class,new Result());
    }

    public static Result get(Class<? extends Exception> e) {
        if(e==null){
            return map.get(Result.class);
        }
        Result o = map.get(e);
        if(o==null){
            try {
                o = new Result(e.newInstance());
                map.put(e,o);
            } catch (Exception e1) {
                throw new RuntimeException(e1);
            }
        }
        return o;
    }
}

public class Flyweight {

    public static void main(String[] args){
        Integer i1 = Integer.valueOf(1);
        Integer i2 = Integer.valueOf(1);
        System.out.println(i1==i2); //true

        Integer i3 = new Integer(1);
        System.out.println(i1==i3); //false

        System.out.println(ResultFlyweight.get(null)); //{ status:0, msg:SUCCESS }
        System.out.println(ResultFlyweight.get(NoSuchElementException.class)); //{ status:1, msg:java.util.NoSuchElementException }
        System.out.println(ResultFlyweight.get(RuntimeException.class)==ResultFlyweight.get(RuntimeException.class)); //true
    }
}
