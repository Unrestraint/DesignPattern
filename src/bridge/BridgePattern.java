package bridge;

/**
 * 桥接模式 - 结构型模式
 * 将抽象部分与他的实现部分分离,这样抽象化与实现化解耦,使他们可以独立的变化.如何实现解耦的呢,就是通过提供抽象化和实现化之间的桥接结构.
 * 一个类存在两个独立变化的维度，且这两个维度都需要进行扩展。
 *
 * 桥接模式与策略模式有异曲同工之妙，策略模式只有一个变化，桥接模式有两个变化
 *
 * 写一个发验证码的例子，（本来想写JDBC的例子，结果写着写着发现和我写的简单工厂没啥区别了）
 * （设计模式虽好，但抽象真难）
 * @author Unrestraint
 */

//抽象信息发送方式
interface Sender{
    void send(String msg,String to);
}

//邮件发送方式
class MailSender implements Sender{

    @Override
    public void send(String msg, String to) {
        System.out.println("向"+to+"发送邮件:"+msg);
    }
}
//短信发送方式
class SmsSender implements Sender{
    @Override
    public void send(String msg, String to) {
        System.out.println("向"+to+"发送短信:"+msg);
    }
}

//抽象内容发送
abstract class Message{
    protected Sender sender;
    Message(Sender sender){
        this.sender = sender;
    }
    public void send(String msg, String to){
        sender.send(msg,to);
    }
}
//验证码信息发送
class CodeMessage extends Message{
    CodeMessage(Sender sender) {
        super(sender);
    }
    @Override
    public void send(String msg, String to) {
        sender.send("验证码："+msg,to);
    }
}
//错误信息发送
class ErrorMessage extends Message{
    ErrorMessage(Sender sender) {
        super(sender);
    }

    @Override
    public void send(String msg, String to) {
        sender.send("系统出错:"+msg,to);
    }
}

//测试
public class BridgePattern {
    public static void main(String[] args){

        //邮箱验证码发送
        Message msg = new CodeMessage(new MailSender());
        msg.send("123456","792583398@qq.com");

        //手机验证码发送
        msg = new CodeMessage(new SmsSender());
        msg.send("123456","88888888888");

        //将错误发送到邮箱
        new ErrorMessage(new MailSender()).send("null point error","792583398@qq.com");
    }
}
