package author.wzn.design_pattern;

/*
 *
 * @Date: 2021/8/10
 * @Description: 多线程设计模式 - 犹豫模式 : 实际做一件事情之前,犹豫一下，别人在做我就不做了
 *               场景: 监控,定时器,对象的单例
 */
public class Balking {

    public static void main(String[] args) throws InterruptedException {
        start();
        start();
        start();
        Thread.sleep(5000);

        stop(t1);
    }

    private static final Object lock = new Object();

    private static volatile boolean isDoing = false;

    private static Thread t1 = null;

    public static void start() {

        if (isDoing) {
            System.out.println("犹豫了-有人在监控");
            return;
        }
        //DLC
        synchronized (lock) {
            if (isDoing) {
                System.out.println("犹豫了-有人在监控");
                return;
            }
            isDoing = true;
        }

        t1 = new Thread(() -> {
            while (isDoing) {
                try {
                    System.out.println("监控-监控");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
            }
        });
        t1.start();
    }

    public static void stop(Thread thread) {
        //不加syn是因为这个方法都是直接设置成false,而开始方法要确保只有一个人执行
        isDoing = false;
        thread.interrupt();
    }
}
