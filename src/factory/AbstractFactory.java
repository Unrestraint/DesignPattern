package factory;

/**
 * 抽象工厂模式
 * 网上的很多例子有点不符合抽象工厂的定义
 *
 * 意图：提供一个创建一系列相关或相互依赖对象的接口，而无需指定它们具体的类。
 * 抽象工厂模式在设计之初需对产品族的结构非常明确，否则很难更改。
 *
 * 这里使用一个电脑的例子来看，当然也是参考别人的比较好的
 * @author Unrestraint
 */

//定义需要的抽象配件
interface CPU{
    void infoCPU();
}
interface GPU{
    void infoGPU();
}


//具体的配件
class I5_6300HqCPU implements CPU{
    @Override
    public void infoCPU(){
        System.out.println("Intel 酷睿i5 6300HQ");
    }
}
class I5_7300HqCPU implements CPU{
    @Override
    public void infoCPU(){
        System.out.println("第七代英特尔 酷睿 i5-7300HQ");
    }
}
class GTX960mGPU implements GPU{
    @Override
    public void infoGPU(){
        System.out.println("NVIDIA GeForce GTX 960M");
    }
}
class GTX1060GPU implements GPU{
    @Override
    public void infoGPU(){
        System.out.println("NVIDIA GeForce GTX 1060");
    }
}



//定义抽象工厂接口
interface LaptopAbstractFactory{
     CPU createCPU();
     GPU createGPU();
}


//实现一个戴尔电脑配置的接口
class Dell7559Factory implements LaptopAbstractFactory{
    @Override
    public CPU createCPU() {
        return new I5_6300HqCPU();
    }

    @Override
    public GPU createGPU() {
        return new GTX960mGPU();
    }

}

//实现一个小米配置的接口
class XiaoMiFactory implements LaptopAbstractFactory{

    @Override
    public CPU createCPU() {
        return new I5_7300HqCPU();
    }

    @Override
    public GPU createGPU() {
        return new GTX1060GPU();
    }
}


//电脑实例
class Computer{
    CPU cpu;
    GPU gpu;
}

//实例化电脑，如果每个工厂都实现newInstance就产生代码冗余，且不好统一
class ComputerFactory{
     static Computer newInstance(LaptopAbstractFactory factory){
        Computer computer = new Computer();
        computer.cpu = factory.createCPU();
        computer.gpu = factory.createGPU();
        return computer;
    }
}

public class AbstractFactory {

    public static void main(String[] args){
        Computer c = ComputerFactory.newInstance(new XiaoMiFactory());
        c.gpu.infoGPU();
        c.cpu.infoCPU();
        c = ComputerFactory.newInstance(new Dell7559Factory());
        c.gpu.infoGPU();
        c.cpu.infoCPU();
    }
}
