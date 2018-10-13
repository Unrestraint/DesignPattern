# DesignPattern
设计模式之路

学了很多设计模式，阿里简历评估时，一个SOLID原则直接让我懵逼，才意识到学习了那么多设计模式，却没有思考为什么这样设计，总结一下：<br>

面对对象SOLID原则也是设计模式五大原则：<br>
SRP The Single Responsibility Principle	单一职责原则<br>
OCP The Open Closed Principle		开放封闭原则<br>
LSP The Liskov Substitution Principle	里氏替换原则<br>
ISP The Interface Segregation Principle	接口分离原则<br>
DIP The Dependency Inversion Principle	依赖倒置原则<br>

## 总结：一个类或接口应该只负责一种类型的事，否则就需要考虑分解；对变化的部分抽象，类之间的关系应依赖于抽象，使用继承抽象类或接口来进行解耦，并保证相同抽象类或接口的子类之间可以相互替换。<br>
<br>

### 1.SRP 单一职责原则：<br>
定义：当需要修改某个类的时候原因有且只有一个（THERE SHOULD NEVER BE MORE THAN ONE REASON FOR A CLASS TO CHANGE）。换句话说就是让一个类只做一种类型责任，当这个类需要承当其他类型的责任的时候，就需要分解这个类。 <br>
理解：一个类只做一个类型的事，如果一个类有多个责任，就需要分解，如定义了一个数据结构，在这个数据结构方法中有与数据结构操作无关的计算，这个时候就需要将该类分解为数据操作和计算两个类。<br>
示例：<br>
定义一个图形接口：<br>
interface Shape{<br>
   String getArea();<br>
}<br>
实现了不同图形的面积计算，那么有时需要格式化打印，有时需要打包为XML或JSON数据，这时候如果将方法直接实现在图形类中，那么每增加一种输出方式，就需要修改类实现，这显然是极其不好的，一种恰当的做法就是使用桥接模式，将输出方式和计算视为两个变化，只用增加新的类，不用修改原来的类，也符合开闭原则。<br>

### 2.OCP 开放封闭原则<br>
定义：类、模块、方法等是可以扩展的，但不可以修改。开即对扩张开放，闭即对修改关闭。开闭原则的应用体现在，开发人员应该仅仅对程序中频繁出现变化的地方进行抽象（封装变化点）。对变化点的封装即对变化的修改关闭。对于变化的不确定性，可随时扩展，即继承的使用、抽象类的运用。<br>
理解：开闭原则就是正确的抽象，将变化的部份抽象出来，实现不变的部份，使出现新的需求时，尽量（抽象我认为是一种尽可能的正确的猜测）不会修改原有代码，如事件驱动模型，抽象出事件发起者，事件，事件接收者。<br>
示例：<br>
说到事件驱动模型，看过很多材料，了解过Qt是如何实现事件分发，也了解过swing，面对不同的事件需求有不同的实现。说说Qt的GUI事件，每一个继承自QObject的对象都可以进行事件处理，Windows将事件发送给Qt程序，Qt将事件打包，通过QApplication::widgetAt获取具体的QWidget，然后调用QWidget::event(QEvent e)进行事件发送，该对象根据继承或者父子关系完成不同类型事件的处理。一个事件一个发送者、一个接收者。处理业务常用的我认为是基于发布订阅模式的（应该是），这时抽象就较为复杂了，单例还是多例，单线程还是多线程，多线程就需要考虑线程安全，考虑锁的性能损耗，进行锁优化，异步还是同步，同步就需要考虑长时间阻塞问题，异步就需要考虑线程池、事件队列和错误处理，那么就会有新的问题，如何保证事件的可用性，有可能事件正在处理崩溃了(越来越靠近消息队列)。<br>
所以我认为使用同步事件可以进行解耦，如果异步事件还是搭建一个可用性高的消息队列，接收消息再进行异步处理，我觉得这是比较好的，spring提供了一套非常简单好用的事件驱动。<br>

### 3.LSP 里氏替换原则<br>
定义：总是保证子类可以替换它的基类。假设有个函数F，其形参是基类B，基类B有个派生类D，如果把D对象作为B类传递给F，会导致F出现错误行为，那么D就违反了LSP。<br>
理解：子类可以替换它们的积累，该原则是限制面对对象中继承、多态。多态中抽象是比较复杂的，有的时候我们不得不对抽象进行限制，如对于出错的处理，是内部处理，还是抛异常，亦或对返回值的限制等等。<br>
示例：<br>
突然想到个搞笑的，老师点到只需要有“到”或者不说话，点到一个人的时候该同学上去把老师揍了一顿，这课还讲个什么，谁关心你来不来，233。<br>

### 4.ISP 接口分离原则<br>
定义：一个接口或者类应该拥有尽可能少的行为。接口的设计很容易会失控，不停地添加功能使得一个接口越来越臃肿，导致接口方法越来越多。<br>
理解：和单一职责原则有些类似，一个针对接口，一个针对类，Java中的集合就是一个很好的例子。<br>

### 5.DIP 依赖倒置原则<br>
定义：高层模块不应该依赖于低层模块。二者都应该依赖于抽象。抽象不应该依赖于细节。细节应该依赖于抽象。<br>
理解：接口就是抽象，实现就是细节，高层模块是调用者，底层模块是被调用者，如果高层模块直接依赖于底层模块，那么底层模块的改动就会影响高层模块。<br>

