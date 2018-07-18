package factory;

import java.io.InputStream;

/**
 * 工厂方法模式
 * 屏蔽了实例化对象的细节，使使用者只需指定要使用的工厂即可，加入新的产品，只需再实现一个该产品的工厂即可，
 * 但每次都要创建新的产品类
 * 工厂具备多态性
 * 可结合简单工厂使用
 * @author Unrestraint
 */

interface ImageReader{
    void read();
}
class JPGReader implements ImageReader{
    JPGReader(InputStream stream){
    }
    @Override
    public void read(){
        System.out.println("read JPG image");
    }
}

class PNGReader implements ImageReader{
    @Override
    public void read() {
        System.out.println("read PNG image");
    }
}

interface ImageReaderFactory{
    ImageReader create();
}
class JPGReaderFactory implements ImageReaderFactory{
    @Override
    public ImageReader create() {
        return new JPGReader(null);
    }
}
class PNGReaderFactory implements ImageReaderFactory{
    @Override
    public ImageReader create(){
        return new PNGReader();
    }
}


public class MethodFactory {

    static ImageReaderFactory getFactory(String classPath) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return (ImageReaderFactory) Class.forName(classPath).newInstance();
    }


    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        ImageReaderFactory factory = new JPGReaderFactory();
        factory.create().read();
        factory = new PNGReaderFactory();
        factory.create().read();

        MethodFactory.getFactory("factory.JPGReaderFactory").create().read();
    }
}
