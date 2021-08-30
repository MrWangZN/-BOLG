package learner.wzn.load;
/*
*
* @Date: 2021/8/22
* @Description: 静态代码块只执行一次。为了保证静态块只能够执行一次,静态代码块是隐藏加锁的。
*               静态代码块没有执行完其他线程没有办法执行使用该类
*/
public class StaticBlockOnlyOnce {
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("t1 --- > start");
            new TestStaticBlockOnlyOnce();
            System.out.println("t1 --- > end");
        }).start();
        new Thread(() -> {
            System.out.println("t2 --- > start");
            new TestStaticBlockOnlyOnce();
            System.out.println("t2 --- > end");
        }).start();

        /*
        * t1 --- > start
        * t2 --- > start
        * 我只会执行一次
        */
        //结论,一个线程执行了静态代码块没有结束,另外一个线程也只能够等待他完成。
    }

}
class TestStaticBlockOnlyOnce{
    static{
        if (true) { //直接使用while(true)不能编译通过
            System.out.println("我只会执行一次");
            while (true) { }
        }
    }
}