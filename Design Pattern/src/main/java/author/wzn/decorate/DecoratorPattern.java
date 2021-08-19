package author.wzn.decorate;


/*
 * 装饰器模式 : Decorator Pattern
 *      定义 : 动态的给一个对象添加一些额外的功能。就增加功能来说,装饰模式比生成之类更为灵活
 *      使用例子: 在输入输出流中大量被使用到通过构造方法传入
 *
 *      装饰器模式的核心是扩展功能
 *      而代理模式是控制访问的,只能够通过代理对象来访问,而不知直接访问对象
 *      代理模式和装饰器模式经常是混合使用的,在代理的基础上进行装饰等
 *      设计模式是为了解决实际问题的,而实际问题通常在代理的基础上进行装饰、装饰后在代理等。
 *      （比如我要代理卖羽绒服,但是样式不够好,我可以再装饰一下)
 *
 * */
public class DecoratorPattern {

    public static void main(String[] args) {
        CleanRobot cleanRobot = new CleanRobot();
        CleanWithSingRobot cleanWithSingRobot = new CleanWithSingRobot(cleanRobot);
        cleanWithSingRobot.cleanWithSing();
    }
}

interface Robot{
    void clean();
}

class CleanRobot implements Robot {

    @Override
    public void clean() {
        System.out.println("打扫卫生");
    }

}
//如果想要丰富功能可以定义为抽象类,让子类来实现更多的装饰器
class CleanWithSingRobot implements Robot {

    Robot robot;

    public CleanWithSingRobot(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void clean() {
        robot.clean();
    }

    public  void cleanWithSing(){
        robot.clean();
        System.out.println("唱歌");
    }

}

