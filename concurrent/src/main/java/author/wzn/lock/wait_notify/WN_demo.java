package author.wzn.lock.wait_notify;

import java.util.concurrent.locks.ReentrantLock;

/*
* @Author: 王振南
* @Date: 2021/8/10
* @Description: wait notify的使用,使用wait会释放当前持有的锁,通过main方法延时了
*               使用notify还需要等待同步代码块结束才能释放锁,t2线程notify后唤醒
*               但是t2没有释放锁t1从waitSet进入到EntryList
*/
public class WN_demo {

    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {

            synchronized (lock) {
                System.out.println("执行到一半,t2线程好兄弟去执行");
                try {
                    lock.wait();
                    System.out.println("执行其他");
                    System.out.println(System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "t1").start();

        new Thread(() -> {

            synchronized (lock) {
                System.out.println(System.currentTimeMillis());
                lock.notify();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis());
                System.out.println("哥们我唤醒你了,但是你还要等我结束你才可以运行");
            }

        }, "t2").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
