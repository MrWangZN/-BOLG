package author.wzn.lock.activity_question;
/*
*
* @Date: 2021/8/10
* @Description: 锁的活跃性问题 - 活锁
*/
public class LiveLock {

    private final static Object lock = new Object();


    public static void main(String[] args) {
        liveLockDemo();
    }

    private static  int count = 50;

    //这个案例中总是一个加一个将，虽然可以得到锁但是没有一个线程可以结束方法  方案的解决添加随机时间就能够错开
    public static void liveLockDemo() {
        new Thread(() -> {
            while(count < 100) {
                synchronized (lock) {
                    System.out.println("t1-"+count);
                    count++;
                }
                //睡眠
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        new Thread(() -> {
            while(count > 0){
                synchronized (lock){
                    System.out.println("t2-"+count);
                    count--;
                }
                //睡眠
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2").start();
    }

}
