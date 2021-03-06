# 有了synchronized为什么要需要Lock？

## 1、synchronized容易产生死锁

### 1.1产生死锁的的条件

☆      多个线程同时被阻塞，它们中的⼀个或者全部都在等待某个资 源被释放。由于线程被⽆限期地阻塞，因此程序不可能正常终⽌。

- **互斥条件**：该资源任意⼀个时刻只由⼀个线程占⽤
- **请求和保持等待**：因请求资源⽽阻塞时，对已获得的资源保持不放
- **不剥夺**：线程已获得的资源在末使⽤完之前不能被其他线程强⾏剥夺，只有⾃⼰使⽤完毕 后才释放资源
- **循环等待**：形成⼀种头尾相接的循环等待资源关系。

☆      **形成死锁需要同时满足以上四个添加，而使用synchronized由于其本身特性的语音只能够通过打破循环等待这条件来破坏死锁（一次性请求所有的资源）**

## 1.2 使用Lock来避免死锁问题

- **可以使用lock.tryLock()尝试获得锁，而不会保持等待释放锁**
- **使用lock.lockInterruptibly()打断**
- **同一个锁对象上可以有多个Condition**

## 2、synchronized是阻塞式同步，而Lock的功能更加丰富

Lock有可重入锁，读写锁，信号量，CountDownLatch、CyclicBarrier来满足各种业务场景。

## 3、Lock的不足之处

- 总是需要我们手动的释放锁，而synchronized在代码块结束能够自动释放

- Java对synchronized做了很多优化，是C\C++实现的，而显式锁效率就要低一些（如果在总是要独占资源的时候）。