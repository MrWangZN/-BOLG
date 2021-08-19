package author.wzn.design_pattern;


/*
*
* @Date: 2021/8/12
* @Description:保护性暂停即 Guarded Suspension，用在一个线程等待另一个线程的执行结果
*              有一个结果需要从一个线程传递到另一个线程，让他们关联同一个 GuardedObject
*              如果有结果不断从一个线程到另一个线程那么可以使用消息队列（见生产者/消费者）
*              JDK 中，join 的实现、Future 的实现，采用的就是此模式
*              因为要等待另一方的结果，因此归类到同步模式
*/
public class GuardedSuspension {

    private volatile Object response;

    private static final Object lock = new Object();

    //无限制的等待下去，除非被打断或者被notify了
    public Object get() {
        synchronized (lock) {
            while (response == null) {
                try {
                    response.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return response;
        }
    }

    //设置超时时间的等待
    public Object get(long timeout) {
        synchronized (lock) {
            long begin = System.currentTimeMillis();
            long timePass = 0;
            long waitTime;
            while (response == null) {

                waitTime = timeout - timePass;//还需要等待的时间
                if (waitTime <= 0) {
                    break;
                }
                try {
                    lock.wait(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timePass = System.currentTimeMillis() - begin;
            }
        }
        return response;
    }

    public void complete(Object response) {
        if(response == null) throw new NullPointerException();
        synchronized (lock) {
            if (response != null) {
                this.response = response;
                lock.notifyAll();
            }
        }
    }

}
