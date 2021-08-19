package author.wzn.lock.activity_question;

import author.wzn.lock.activity_question.entity.Chopsticks;
import author.wzn.lock.activity_question.entity.Philosopher;

/*
*
* @Date: 2021/8/10
* @Description: 锁的活跃性问题 - 饥饿
*               还可以用哲学家问题进行改造
*/
public class Starvation {

    public static void main(String[] args) {
        starvation();
    }

    //饥饿现象产生 阿基米德吃不上饭，而赫拉克利特总是可以执行 而且并没有发生死锁
    public static void starvation() {
        Chopsticks c1 = new Chopsticks(1);
        Chopsticks c2 = new Chopsticks(2);
        Chopsticks c3 = new Chopsticks(3);
        Chopsticks c4 = new Chopsticks(4);
        Chopsticks c5 = new Chopsticks(5);

        new Philosopher("苏格拉底", c1, c2).start();
        new Philosopher("柏拉图", c2, c3).start();
        new Philosopher("亚里士多德", c3, c4).start();
        new Philosopher("赫拉克利特", c4, c5).start();
        new Philosopher("阿基米德", c1,c5).start();
    }
}
