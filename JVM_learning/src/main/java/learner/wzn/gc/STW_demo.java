package learner.wzn.gc;


import java.util.ArrayList;
import java.util.List;
//验证存在STW

public class STW_demo {
    public static void main(String[] args) {
        WorkThread work = new WorkThread();
        PrintThread print = new PrintThread();
        print.start();
        work.start();
    }
    public static class WorkThread extends Thread{
        List<byte[]> list = new ArrayList<>();
        @Override
        public void run() {
            try {
                while (true) {
                    for (int i = 0; i < 1000; i++) {
                        byte[] buffer = new byte[1024];
                        list.add(buffer);
                    }
                    if (list.size() > 10000) {
                        list.clear();
                        System.gc();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class PrintThread extends Thread{
        public final long startTime = System.currentTimeMillis();
        @Override
        public void run() {
            try {
                while (true) {
                    long l = System.currentTimeMillis() - startTime;
                    System.out.println(l / 1000 + "." + l % 1000);
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
