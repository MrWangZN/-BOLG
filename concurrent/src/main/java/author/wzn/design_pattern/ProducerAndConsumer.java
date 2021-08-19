package author.wzn.design_pattern;

import java.util.LinkedList;

public class ProducerAndConsumer {


    public static void main(String[] args) {
        Queue queue = new Queue(3);

        Producer apple = new Producer(queue, "苹果", 5);
        // 也可以让生产者一直生产这个 while(true)
//        Producer apple2 = new Producer(queue, "苹果2", 5);
//        Producer banana = new Producer(queue, "香蕉", 3);
//        Producer li = new Producer(queue, "梨", 3);
        apple.start();
//        apple2.start();
//        banana.start();
//        li.start();
        Consumer consumer = new Consumer(queue);
        consumer.start();
    }
}

class Producer extends Thread{
    private final Queue queue;
    String name;
    int price;
    //初始化的时候就定义生产后放在那里
    public Producer(Queue queue, String name, int price) {
        this.name = name;
        this.price = price;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            queue.put(new Product(name, price));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer extends Thread{

    private final Queue queue;

    public Consumer(Queue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(queue.get());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Product {

    private final String name;
    private final int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }


    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
class Queue{

    private final LinkedList<Product> queue;
    private volatile int capacity;

    public Queue(int capacity) {
        this.capacity = capacity;
        queue = new LinkedList<Product>();
    }

    public void put(Product product) {
        synchronized (queue) {

            while(queue.size() + 1 > capacity){
                try {
                    System.out.println("-----已经满了,别给我了------");
                    queue.wait();
                } catch (InterruptedException e) {
                    e.getStackTrace();
                }
            }
            if (queue.size() < capacity) {
                queue.add(product);
                queue.notify();
            }
        }
    }

    public Product get() {
        synchronized (queue) {
            while(queue.size() == 0){
                try {
                    System.out.println("------------没东西吃我等等----------");
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Product product = queue.removeFirst();
            queue.notifyAll();
            return product;
        }
    }
}