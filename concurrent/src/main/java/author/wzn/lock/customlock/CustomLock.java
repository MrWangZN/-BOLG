package author.wzn.lock.customlock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
/*
* @Author: 王振南
* @Date: 2021/8/19
* @Description: 个人尝试  :  实现不可重入和可重入的锁   主要是通过模仿ReentrantLock
*/
public class CustomLock implements Lock {

    private final CustomSync sync;

    public CustomLock() {
        this(true); //默认使用不可同步
    }

    public CustomLock(boolean isNotReentrant){
        //true为不可冲入，false为可重入
        sync = isNotReentrant ? new CustomSyncNonReentrant() : new CustomSyncReentrant();
    }

    @Override
    public void lock() {
        sync.lock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();

    }

    public CustomLock.CustomSync getCustomSync() {
        return sync;
    }

    public boolean isLocked() {
        return sync.isLocked();
    }

    static abstract class CustomSync extends  AbstractQueuedSynchronizer { //抽象同步器

        abstract void lock();

        final ConditionObject newCondition() {
            return new ConditionObject();
        }

        // Methods relayed from outer class
        final Thread getOwner() {
            return getState() == 0 ? null : getExclusiveOwnerThread();
        }

        final int getHoldCount() {
            return isHeldExclusively() ? getState() : 0;
        }

        final boolean isLocked() {
            return getState() != 0;
        }

        /*
         *
         * Reconstitutes the instance from a stream (that is, deserializes it).
         *
         */
        private void readObject(java.io.ObjectInputStream s)
                throws java.io.IOException, ClassNotFoundException {
            s.defaultReadObject();
            setState(0); // reset to unlocked state
        }

        @Override
        protected boolean tryRelease(int arg) {
            int c = getState() - arg;
            if (Thread.currentThread() != getOwner())
                throw new IllegalMonitorStateException();
            if (c == 0) {
                setExclusiveOwnerThread(null);
                setState(c);
                return true;
            }
            setState(c);
            return false;
        }
    }
     static class CustomSyncNonReentrant extends CustomSync {  //具体实现一个不可重入
        protected CustomSyncNonReentrant() {
            super();
        }

        @Override
        public boolean tryAcquire(int arg){
            if (arg == 1) {
                if (compareAndSetState(0, 1)) {
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
            }
            return false;
        }

        @Override
        void lock() {
            acquire(1);
        }
    }
    static class CustomSyncReentrant extends CustomSync {     //具体实现一个可重入
        protected CustomSyncReentrant() {
            super();
        }

        @Override
        public boolean tryAcquire(int arg){ //TODO 修改为可以重入
            if (arg == 1) {
                if (compareAndSetState(0, 1)) {
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
            }
            return false;
        }

        @Override
        void lock() {
            Thread current = Thread.currentThread();
            if (getState() >= 1 && getOwner() == current) {
                setState(getState() + 1);
            } else {
                acquire(1);
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        CustomLock customLock = new CustomLock();
        new Thread(() -> {
            try {
                customLock.lock();
                System.out.println("lock first");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                customLock.unlock();
            }
        }).start();
        new Thread(() -> {
            try {
                customLock.lock();
                System.out.println("lock second");
            }  finally {
                customLock.unlock();
            }
        }).start();

        Thread.sleep(4000);


        System.out.println("---------------------------------------");


        CustomLock customLock1 = new CustomLock(false);
        new Thread(() -> {
            try {
                customLock1.lock();
                System.out.println("lock first");
                try {
                    Thread.sleep(2000);
                    customLock1.lock();
                    System.out.println("lock second");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("释放第二次加锁");
                    customLock1.unlock();
                }
            }  finally {
                System.out.println("释放第一次加锁");
                customLock1.unlock();
            }
        }).start();

        Thread.sleep(1000);
        System.out.println("线程二尝试加锁");
        new Thread(() -> {
            try {
                customLock1.lock();
                System.out.println("线程二获取锁");
            } finally {
                System.out.println("释放锁");
                customLock1.unlock();
            }
        }).start();
    }
}
