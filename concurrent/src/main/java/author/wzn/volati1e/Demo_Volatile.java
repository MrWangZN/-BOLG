package author.wzn.volati1e;
/*
* @Author: 王振南
* @Date: 2021/8/10
* @Description: 验证volatile可以保证数据可见性，和防止指令重排
*/
public class Demo_Volatile {

    public static void main(String[] args) throws InterruptedException {

        visible();
//        reset();
    }

    public static boolean flag = false;
    //添加了volatile
    //public static volatile boolean flag = false;

    public static final Object lock = new Object();
    public static void visible() throws InterruptedException {
        new Thread(() -> {
            while (!flag) {
                synchronized (lock) {

                }
            }
        }).start();
        Thread.sleep(1000);
        flag = true;
    }

    public static int num = 0;

    //指令重排不好演示，需要用到工具来演示两个线程并发。
    //第一种情况  num = 1
    //第二种情况  num = 4
    //第三种情况  num = 2
    //第四种情况  num = 0  t1,t2同时执行时发生指令重排，t2线程先执行了flag = true,然后执行num = 2;

    // 问题解决给flag变量添加volatile,原因:volatile能够防止指令重排
    // 原理:如下
    // volatile 变量的写指令后会加入写屏障
    // volatile 变量的读指令前会加入读屏障
    public static void reset() throws InterruptedException{

        new Thread(() -> {
            if (flag) {
                num = num * 2;
            }else{
                num = 1;
            }
        },"t1").start();

        new Thread(() -> {
            num = 2;
            flag = true;
        },"t2").start();

        System.out.println(num);
    }


}
