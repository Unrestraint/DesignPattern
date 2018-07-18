package factory;

import java.util.NoSuchElementException;

/**
 * 简单工厂模式
 * @author Unrestraint
 */

//实体抽象接口
interface Shape{
    void draw();
}
//实现接口
class Circle implements Shape{
    private int w=1,h=1;
    public void set(int w,int h){
        this.w=w;
        this.h=h;
    }
    public void draw(){
        System.out.println("draw circle "+w+" "+h);
    }
}
class Square implements Shape{
    private int r=2;
    public void setR(int r){
        this.r=r;
    }
    public void draw(){
        System.out.println("draw Square "+r);
    }
}

/**
 * 简单工厂模式
 */
public class SimpleFactory {
    /**
     * 根据指定对象id进行实例化对象，简单但违反开闭原则
     */
    static Shape getShape(String type){
        switch(type){
            case "Circle":return new Circle();
            case "Square":return new Square();
            default:throw new NoSuchElementException(type);
        }
    }

    /**
     * 利用反射加泛型定义实例化对象
     */
    static Shape getShape(Class<? extends Shape> c){
        try {
            return c.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("创建实例失败",e);
        }
    }

    /**
     * 利用类名+反射实例化对象，实现实例化可配置，spring 应该是这种方式
     */
    static Shape getShapeByClass(String classPath) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class c = Class.forName(classPath);
        return  (Shape)c.newInstance();
    }


    public static void main(String[] arg) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        SimpleFactory.getShape("Circle").draw();
        SimpleFactory.getShape(Square.class).draw();
        SimpleFactory.getShapeByClass("factory.Circle").draw();
    }

}

