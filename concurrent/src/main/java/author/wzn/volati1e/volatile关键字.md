# volatile关键字

### volatile有什么用

1、多线程之间可见性

2、禁止指令重排序（有序性）

<font color = 'red'>**注意：volatile不能保证操作的原子性，修饰的变量同样可能出现线程安全问题**</font>

### volatile怎么使用

只能用来修饰java变量

### 为什么要用volatile

在多线程并发的情况下，会出现指令重排和数据不可见性（程序从cpu高速缓存中读取数据）而导致的并发问题出现

<a>案例地址</a>

### 案例演示 — volatile保证数据可见性

```java
public static boolean flag = false; //只需要添加上volatile就能够正常退出循环
//添加了volatile
//public static volatile boolean flag = false;
public static void visible() throws InterruptedException {
    
        new Thread(() -> {
            while (!flag) {
            }
        }).start();
        Thread.sleep(1000);
        flag = true;
    
}
```

☆ 	上面的例子会陷入死循环，原因在于程序在读取flag的值总是从cpu高速缓存中读取，而非从主存中读取flag的值，导致即使修改了flag为true也无法退出循环

### 案例演示 — volatile防止指令重排

```java
public static int num = 0;
public static boolean flag = false;
//public static volatile boolean flag = false;
public static void reset() throws InterruptedException{

        new Thread(() -> {
            if (flag) {
                num = num * 2;
            }else{
                num = 1;
            }
        },"t1").start();

        new Thread(() -> {
            num = 2;
            flag = true;
        },"t2").start();

        System.out.println(num);
    }
```

☆ 	线程t1,t2同时执行时如果发生指令重排，t2的代码执行变成如下，导致t1线程读取到num的值为0，就会将num = num * 2 ;(0)  

☆	 同样只需要给flag添加上volatile就能够正常退出循环（此时不需要给num变量添加，原因见volatile 原理）

```java
	flag = true;
	num = 2;
```



### volatile 原理

☆ 	volatile 的底层实现原理是内存屏障，Memory Barrier（Memory Fence）

-  对 volatile 变量的写指令后会加入写屏障
  - 写屏障会确保指令重排序时，不会将写屏障之前的代码排在写屏障之后
  - 写屏障（sfence）保证在该屏障之前的，对共享变量的改动，都同步到主存当中
-  对 volatile 变量的读指令前会加入读屏障
  - 读屏障会确保指令重排序时，不会将读屏障之后的代码排在读屏障之前
  - 读屏障（lfence）保证在该屏障之后，对共享变量的读取，加载的是主存中最新数据

**总结：volatile修饰的变量的前后指令不会重排，并且变量的读取都能够从主存中读取**



# volatile和synchronized的区别

**为什么讨论volatile和synchronized呢？ volatile常被成为轻量级的synchronized，但是他不能够保证原子性操作**

### synchronized的作用

- 多线程之间可见性
- 多线程之间有序性
- 多线程之间原子性

### synchronized保证数据可见性

☆	既然synchronized可以保证数据的可见性上面的案例也可以使用synchronized咯，是的

```java
public static final Object lock = new Object();

public static void visible() throws InterruptedException {
    new Thread(() -> {
        while (!flag) {
            synchronized (lock) {

            }
        }
    }).start();
    Thread.sleep(1000);
    flag = true;
}
```

☆	synchronized能够保证数据可见性为什么还要使用volatile？

- ​    synchronized无法解决指令重排问题
- ​    synchronized是重量级锁，而volatile是轻量级的，执行更快，效率更高

### synchronized保证多线程之间有序性

☆	synchronized的有序性不是指令执行的有序性，他并不能防止指令重排，这也是为什么单例模式—懒汉模式需要使用volatile

```java
public class LazySingleton {

    private static volatile LazySingleton instance;

    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if(instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}
```

### final关键字修饰变量

☆	为什么在这里提到了final关键字，因为final在修饰变量也会通过 putfield 指令来完成，同样在这条指令之后也会**加入写屏障**，保证其他线程在读取的时候不会存在为0的情况。**即final也和volatile一样保证了可见性。**如下面例子中，使用了final，实例化对象的时候，通过写屏障不会将对象的赋值操作重排到写屏障之前，保证了变量a的可见。

```java
public class TestFinal {
 final int a = 20;
}
```

☆	其字节码文件

```java
0: aload_0
1: invokespecial #1 // Method java/lang/Object."<init>":()V
4: aload_0
5: bipush 20
7: putfield #2 // Field a:I
 <-- 写屏障
10: return
```

☆	final的其他特性如final方法不能被重写，final类不能被继承等特性不是这个的重点。除此之外**final修饰的类、方法、变量的性能要高一些，JVM有机会进行估计优化。**（需要结合JVM的知识）

