package learner.wzn.gc;

public class SystemGCTest {
    public static void main(String[] args) {
        new SystemGCTest();
        //提醒JVM的垃圾回收器执行gc,当时不确定是否马上执行gc
        //即"gbg clean"可能未打印程序就结束了（垃圾回收器没有立刻执行）
        System.gc();

        System.runFinalization();
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("gbg clean");
    }
}
