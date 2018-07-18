package singleton;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class VolatileTest {
    public static int i=0;
    public static void test(){
        synchronized (VolatileTest.class){
            i++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int size=20;
        ThreadPoolExecutor pool = new ThreadPoolExecutor(size,size,40,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(size));
        for(int i=0;i<size;i++){
            pool.execute(()->{int j=0;while (j++<20)test();});
        }
        Thread.sleep(2000);
        System.out.println(i);
        pool.shutdown();
    }
}
