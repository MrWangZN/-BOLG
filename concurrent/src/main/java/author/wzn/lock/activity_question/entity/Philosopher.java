package author.wzn.lock.activity_question.entity;



public class Philosopher extends  Thread{

    private final String name;
    private final Chopsticks left;  //使用final确保变量不可变 线程安全，参考String类（idea都会提示）
    private final Chopsticks right;

    public Philosopher(String name, Chopsticks left, Chopsticks right) {
        this.name = name;
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        eat();
//        eatDeadLock();
    }

    private void eat() {
        while (true) {
            if (left.tryLock()) {//也可以使用锁超时避免无限制的等待下去
                try {
                    if (right.tryLock()) {
                        try {
                            System.out.println(name+"可以吃饭了   左边筷子-"+left.getId()+"   有边筷子-"+right.getId());
                        }finally {
                            right.unlock();
                        }
                    }
                }finally {
                    left.unlock();
                }
            }
        }
    }

    //这时死锁的
    private void eatDeadLock() {
        while (true) {
            synchronized (left) {
                synchronized (right) {
                    System.out.println(name+"可以吃饭了   左边筷子-"+left.getId()+"   有边筷子-"+right.getId());
                }
            }
        }
    }

}
