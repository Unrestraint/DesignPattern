package singleton;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 单例关于volatile的测试
 * emm没测出什么
 * volatile保证可见性
 * 1）保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。
 * 2）禁止进行指令重排序。
 *
 * 同步块存在如下语义：
 * 1.进入同步块，访问共享变量会去读取主存
 * 2.退出同步块，本地内存对共享变量的修改会立即刷新到主存
 *
 * 所以volatile在懒汉式中保证了指令不会重排序，但我并没有测出结果
 * @author Unrestraint
 */
public class SingletonTest {
    private static SingletonTest singleton;
    private byte[] t = new byte[2];
    private byte[] b;
    private SingletonTest(){
        System.out.println(1);
        b = new byte[2];
    }

    public static SingletonTest getSingleton() throws InterruptedException {
        if(singleton==null){
            synchronized (SingletonTest.class){
                System.out.println("wait");
                SingletonTest.class.wait();
                if(singleton==null){
                    singleton = new SingletonTest();
                    singleton.t[1]='s';
                    singleton.b[1]='s';

                }
                System.out.println("j"+singleton.b[1]);
            }
        }
        return singleton;
    }

    public static void main(String[] args) throws InterruptedException {
        class TestRunnable implements Runnable{
            int i;
            public TestRunnable(int i){
                this.i=i;
            }
            @Override
            public void run() {
                try {
                    SingletonTest s;
                    s = SingletonTest.getSingleton();
                    if(i==1){
                        s.t[1]='a';
                    }
                    Thread.sleep(10);

                    System.out.println("i"+s.t[1]);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        int size=200;

        ThreadPoolExecutor pool = new ThreadPoolExecutor(size,size,40,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(size));

        for(int i=0;i<size;i++){
            pool.execute(new TestRunnable(i));
        }
        Thread.sleep(2000);
        synchronized (SingletonTest.class){
            SingletonTest.class.notifyAll();
        }
        pool.shutdown();
    }
}
