package author.wzn.design_pattern;


/*
* @Author: 王振南
* @Date: 2021/8/11
* @Description: 多线程设计模式 - 两阶段终止
*
*               interrupt()     中断这个线程。
*               interrupted()   测试当前线程是否中断    并且会清楚标记
*               isInterrupted() 测试这个线程是否被中断  不会清处标记
*
*/
public class TwoPhaseTermination {
    public static void main(String[] args) throws InterruptedException {
        //利用 isInterrupt
        Task_1 t1 = new Task_1();
        start(t1);
        Thread.sleep(2000);
        stop(t1);

        //利用打断标记
        Task_2 t2 = new Task_2();
        start(t2);
        Thread.sleep(2000);
        stop(t2);
    }


    public static void start( Thread thread ){

        thread.start();

    }

    public static void stop( Thread thread ){

        thread.interrupt();

    }

}


//两阶段终止 - 使用interrupt()
class Task_1 extends Thread {

    @Override
    public void run() {
        while (true) {
            Thread current = Thread.currentThread();
            if (Thread.interrupted()) {
                System.out.println("料理后事");
                break;
            }
            try {
                System.out.println("监控 - 监控");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
//      源码注释   its interrupt status will be cleared and it will receive an InterruptedException
//                捕获到InterruptedException时会清楚打断标记  因此需要再次打断
                System.out.println(current.isInterrupted());   //打印看一下    false
                current.interrupt();
                System.out.println(current.isInterrupted());   //再次打印看一下 true
            }
        }
    }
}


//两阶段终止 - 使用volatile修饰的变量
class Task_2 extends Thread{

    private volatile boolean stop = false;  //保证stop总是从主内存中读取最新数据

    @Override
    public void run() {
        while (true) {
            if (stop) {
                System.out.println("料理后事");
                break;
            }
            try {
                System.out.println("监控 - 监控");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                stop = true;
            }
        }
    }
}
