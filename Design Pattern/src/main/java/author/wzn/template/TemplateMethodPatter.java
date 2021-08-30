package author.wzn.template;


/*
*
* @Date: 2021/8/19
* @Description: 定义一个操作中的算法的框架，而将一些步骤延迟到子类中。是的子类可以不改变一个算法的结构
*               即可重定义该算法的某些特定步骤
*     将不变的方法,方法父类中。可变得方法方法子类中扩展实现   - 属于行为型模式的一种
*   缺点: 类增多, 复杂度高
*   优点: 复合开闭原则,扩展性强
*/
public class TemplateMethodPatter {
    public static void main(String[] args) {
        Cook food = new TomatoEgg();
        food.cooking();
    }
}


//将不变的方法,方法父类中。
abstract class Cook{

    abstract void step_1(); //一些步骤延迟到子类中
    abstract void step_2(); //一些步骤延迟到子类中

    public void cooking(){ //一个操作中的算法的框架
        step_1();
        step_2();
    }
}
//定义该算法的某些特定步骤
class TomatoEgg extends  Cook{

    @Override
    void step_1() {
        System.out.println("放西红柿");
    }

    @Override
    void step_2() {
        System.out.println("放鸡蛋");
    }
}

