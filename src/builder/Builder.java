package builder;

/**
 * 将一个复杂的构建与其表示相分离，使得同样的构建过程可以创建不同的表示。
 * 一些基本部件不会变，而其组合经常变化的时候。
 *
 *
 * （1）Builder：给出一个抽象接口或抽象类，以规范产品的建造。这个接口规定要实现复杂对象的哪些部分的创建，并不涉及具体的对象部件的创建，一般由子类具体实现。
 * （2）ConcreteBuilder：Builder接口的实现类，并返回组建好对象实例。
 * （3）Director：调用具体建造者来创建复杂对象的各个部分，在指导者中不涉及具体产品的信息，只负责保证对象各部分完整创建或按某种顺序创建。
 * （4）Product：要创建的复杂对象，产品类。
 *
 * 这里利用java自带的StringBuilder类做实例，
 * StringBuilder与StringBuffer是java中的建造者模式
 */
//具体的对象
 class Product{
    StringBuilder builder;
    @Override
    public String toString(){
        return builder.toString();
    }
}
//声明接口
public interface Builder {
     Builder append(Object c);
     Product getProduct();
}
//实现,这里返回this，可以一行代码进行多个配置
class CharBuilder implements Builder{
    StringBuilder builder = new StringBuilder();

    @Override
    public CharBuilder append(Object c) {
        builder.append(c);
        return this;
    }
    public CharBuilder append(char c){
        builder.append(c);
        return this;
    }

    @Override
    public Product getProduct() {
        Product p = new Product();
        p.builder=builder;
        return p;
    }
}

//这个角色不一定必须，可以在使用时直接使用Builder类构建Product
//也可以针对builder实现多个Director，针对不同业务返回不用的Product对象
class Director{
     public static Product constructProduct(CharBuilder builder){
         return builder.append('1').append(builder).getProduct();
     }
}

class Main{
     public static void main(String[] args){
         System.out.println(Director.constructProduct(new CharBuilder()));
     }
}


