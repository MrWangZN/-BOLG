package author.wzn.lock.activity_question.entity;

import java.util.concurrent.locks.ReentrantLock;

/*
*
* @Date: 2021/8/10
* @Description: 解决死锁时extends ReentrantLock来尝试解决
*/
public class Chopsticks extends ReentrantLock
{

    private final int id;

    public int getId() {
        return id;
    }

    public Chopsticks(int id){
        this.id = id;
    }
}