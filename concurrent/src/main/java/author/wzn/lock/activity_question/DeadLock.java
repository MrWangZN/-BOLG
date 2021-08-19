package author.wzn.lock.activity_question;


import author.wzn.lock.activity_question.entity.Chopsticks;
import author.wzn.lock.activity_question.entity.Philosopher;

/*
*
* @Date: 2021/8/10
* @Description: 锁的活跃性问题 - 死锁
*               demo-1 - normal - 普通
*               demo-1 - 使用哲学家就餐问题
*/
public class DeadLock {

    public static void main(String[] args) throws InterruptedException {

//        normal();
//        normaSolve();

//        philosophers(); //与normal不同，一开始可能没有竞争，当每一个philosopher都持有一个筷子的时候发生了死锁现象
        philosophersSolve();
    }

    //基本的死锁现象demo
    public static void normal() throws InterruptedException {

        Object resource_1 = new Object();
        Object resource_2 = new Object();

        new Thread(() -> {

            synchronized (resource_1) {

                System.out.println("t1锁住resource_1执行业务代码");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resource_2) {

                }
            }

        }, "t1").start();
        new Thread(() -> {
            synchronized (resource_2) {
                System.out.println("t2锁住resource_2执行业务代码");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resource_1) {

                }
            }
        }, "t2").start();

        Thread.sleep(100);
    }

    public static void normaSolve(){
        Object resource_1 = new Object();
        Object resource_2 = new Object();

        new Thread(() -> {

            synchronized (resource_1) {

                System.out.println("t1锁住resource_1执行业务代码");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resource_2) {
                    System.out.println("t1锁住resource_2执行业务代码");
                }
            }

        }, "t1").start();
        new Thread(() -> {
            synchronized (resource_1) {
                System.out.println("t2锁住resource_1执行业务代码");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resource_2) {
                    System.out.println("t2锁住resource_2执行业务代码");
                }
            }
        }, "t2").start();
    }

    //The Dinning Philosophers Problem
    public static void philosophers(){
        Chopsticks c1 = new Chopsticks(1);
        Chopsticks c2 = new Chopsticks(2);
        Chopsticks c3 = new Chopsticks(3);
        Chopsticks c4 = new Chopsticks(4);
        Chopsticks c5 = new Chopsticks(5);

        new Philosopher("苏格拉底", c1, c2).start();
        new Philosopher("柏拉图", c2, c3).start();
        new Philosopher("亚里士多德", c3, c4).start();
        new Philosopher("赫拉克利特", c4, c5).start();
        new Philosopher("阿基米德", c5, c1).start();

    }

    //使用可重入锁解决哲学家就餐问题
    public static void philosophersSolve() {
        Chopsticks c1 = new Chopsticks(1);
        Chopsticks c2 = new Chopsticks(2);
        Chopsticks c3 = new Chopsticks(3);
        Chopsticks c4 = new Chopsticks(4);
        Chopsticks c5 = new Chopsticks(5);

        new Philosopher("苏格拉底", c1, c2).start();
        new Philosopher("柏拉图", c2, c3).start();
        new Philosopher("亚里士多德", c3, c4).start();
        new Philosopher("赫拉克利特", c4, c5).start();
        new Philosopher("阿基米德", c5, c1).start();
    }
}
