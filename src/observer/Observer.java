package observer;

import java.util.Observable;

/**
 * 观察者模式
 * 观察者模式是一种一对多的依赖关系
 * 当一个对象被修改时，则会自动通知它的依赖对象。观察者模式属于行为型模式。
 *
 * 一个消息关联很多处理时，可以使用观察者模式进行解耦。
 *
 * java 中的GUI部分，Qt中的信号槽都是观察者模式
 * Java也自带了观察者接口
 *
 * @author Unrestraint
 */

//java示例
class Subject  extends Observable {
    void setMessage(String msg){
        this.setChanged();
        this.notifyObservers(msg);
    }
    String getInfo(){
        return "subject";
    }
}

public class Observer implements java.util.Observer{
    /**
     * 通知Observer状态修改
     * @param o   采用拉取方式获取数据
     * @param arg  采用推送方式获取数据
     */
    @Override
    public void update(Observable o, Object arg) {
        System.out.println(arg);
    }
}
class Observer2 implements java.util.Observer{

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Subject)
            System.out.println(((Subject) o).getInfo());
    }
}

class Main{
    public static void main(String[] args){
        Subject subject = new Subject();
        //先注册的后通知
        subject.addObserver(new Observer());
        subject.addObserver(new Observer());
        subject.addObserver(new Observer2());
        subject.setMessage("test");
    }
}


