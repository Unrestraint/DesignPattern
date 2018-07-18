package singleton;


/**
 *1.懒汉式，线程安全
 * 利用java类加载机制，类加载时，实例化对象
 */
public class Singleton{
    private static Singleton s = new Singleton();
    private Singleton(){
        System.out.println("初始化！");
    }

    public static Singleton getInstance(){
        return s;
    }
}
//测试，等待2秒后输出初始化信息
class Test{
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(2000);
        Singleton.getInstance();
    }
}


/**
 * 2.懒汉式，线程不安全
 */
class LazySingleton{
    private static LazySingleton lazySingleton;

    private LazySingleton(){}

    public static LazySingleton getInstance(){
        if(lazySingleton==null){
            lazySingleton=new LazySingleton();
        }
        return lazySingleton;
    }
}


/**
 * 3.懒汉式，线程安全(不讨论直接在方法上加锁)
 * 双重锁,关于volatile关键字，我未测出问题，建议加上
 */
class LazySyncSingleton{
    private static volatile LazySyncSingleton lazySyncSingleton;

    private LazySyncSingleton(){}

    public static LazySyncSingleton getInstance(){
        if(lazySyncSingleton==null){
            synchronized (LazySyncSingleton.class){
                if(lazySyncSingleton==null){
                    lazySyncSingleton = new LazySyncSingleton();
                }
            }
        }
        return lazySyncSingleton;
    }
}

/**
 * 4.静态内部类，线程安全
 * 内部类加载时构建
 */
class StaticClassLazySingleton{
    private StaticClassLazySingleton(){}

    static class SingletonHolder{
        private static final StaticClassLazySingleton singleton = new StaticClassLazySingleton();
    }

    public static StaticClassLazySingleton getInstance(){
        return SingletonHolder.singleton;
    }

}

