package subscribe;

/**
 * 发布订阅模式
 * 凭理解写的
 *
 * 采用分段锁,每一个主题一把锁
 * 也许有没有发现的bug
 *
 * @author Unrestraint
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 中介
 */
interface Broker{
    void publish(String queue,Object args);
    void addSubscribe(String queue,Subscribe sub);
    void deleteSubscribe(String queue,Subscribe sub);
}

/**
 * 中介具体实现
 */
class PublishSubscribeBroker implements Broker{
    Map<String,ArrayList<Subscribe>> map = new ConcurrentHashMap<>();

    @Override
    public void publish(String queue, Object args) {
        ArrayList list = map.get(queue);

        if(list!=null){
            Subscribe[] subscribes;
            synchronized (list){
                subscribes  = Arrays.copyOf(list.toArray(),list.size(),Subscribe[].class);
            }
            notify(queue,args,subscribes);
        }
    }

    @Override
    public void addSubscribe(String queue, Subscribe sub) {
        if(sub==null||queue==null){
            throw new NullPointerException();
        }
        if(queue.isEmpty()){
            throw new RuntimeException("queue cannot be empty");
        }

        ArrayList<Subscribe> list = map.get(queue);
        //该情况有可能发生一个问题，订阅表有一个订阅者正在删除，第一个新的订阅者在删除前获取了表，第二个订阅者在删除后新建了表
        //因为map.get有可能返回null,不能对其进行加锁，所以先获取list
        if(list==null){
            synchronized (this){
                if(!map.containsKey(queue)){
                    list = new ArrayList();
                    map.put(queue,list);
                }else
                    list = map.get(queue);
            }

        }
        //锁表
        synchronized (list){
            //如果获取的表锁和map中保存的一样，插入
            if(list==map.get(queue)){
                if(!list.contains(sub)){
                    list.add(sub);
                }
                return;
            }
        }
        //获取的表和map中不一样或者因为删除map中没有表了，重新尝试
        addSubscribe(queue,sub);
    }


    @Override
    public void deleteSubscribe(String queue, Subscribe sub) {
        ArrayList list = map.get(queue);
        if(list!=null){
            synchronized (list){
                if(list.remove(sub)){
                    //成功删除后，检查一下是否为空
                    if(list.isEmpty()){
                        synchronized (this){
                            map.remove(queue);
                        }
                    }
                }
            }
        }
    }

    protected void notify(String queue, Object args, Subscribe[] subscribes){
        for(Subscribe subscribe:subscribes){
            subscribe.subscribe(queue,args);
        }
    }
}

//订阅者接口
interface Subscribe{
    void subscribe(String queue,Object args);
}


//订阅者
class TestSub implements Subscribe{

    @Override
    public void subscribe(String queue, Object args) {
        System.out.println(queue+args);
    }
}

//测试
public class PublishSubscribe {
    public static void main(String[] args){
        Broker broker = new PublishSubscribeBroker();
        broker.addSubscribe("test",new TestSub());
        broker.addSubscribe("demo",new TestSub());
        broker.publish("test","消息内容");

        Subscribe sub = new TestSub();
        new Thread(()->{
            int i=100000;
            while (i-->0) broker.deleteSubscribe("queue",sub);
        }).start();

        new Thread(()->{
            int i=100000;
          while(i-->0) broker.addSubscribe("queue", sub);
        }).start();

        Subscribe sub2 = new TestSub();
        new Thread(()->{
            int i=100000;
            while(i-->0) broker.deleteSubscribe("queue", sub2);
        }).start();

        new Thread(()->{
            int i=100000;
            while(i-->0) broker.addSubscribe("queue", sub2);
        }).start();
    }
}
