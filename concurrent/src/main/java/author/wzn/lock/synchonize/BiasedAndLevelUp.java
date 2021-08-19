package author.wzn.lock.synchonize;

import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;

public class BiasedAndLevelUp {

    public static void main(String[] args) throws InterruptedException {
        toHighWeight();
    }

    //显示偏向
    private static void showBiased() throws InterruptedException {
        Thread.sleep(5000);//HotSpot 虚拟机在启动后有个 4s 的延迟才会对每个新建的对象开启偏向锁
        new Thread(()->{
            Dog lock = new Dog();
            ClassLayout classLayout = ClassLayout.parseInstance(lock);
            synchronized (lock) {
                System.out.println(classLayout.toPrintable());
            }
        }).start();
    }

    private static void toHighWeight() throws InterruptedException {
        Thread.sleep(5000);
        ArrayList<Dog> list = new ArrayList<>();
            new Thread(() -> {
                Dog lock = new Dog();
                synchronized (lock) {
                    list.add(lock);
                    ClassLayout classLayout = ClassLayout.parseInstance(lock); //101  Biased
                    System.out.println("t1");
                    System.out.println(classLayout.toPrintable());
//                try {
//                    Thread.sleep(1000); //获取锁后睡眠一秒让另外一个线程来竞争
                    System.out.println(classLayout.toPrintable()); //10  重量级锁
//                }
//                catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                }
            }).start();
            new Thread(()->{
                try {
                    Thread.sleep(2136);
                    System.out.println("t2");
                    synchronized (list.get(0)) {
                        System.out.println("t2");
                        System.out.println(ClassLayout.parseInstance(list.get(0)).toPrintable());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

    }
}
class Dog{

}
