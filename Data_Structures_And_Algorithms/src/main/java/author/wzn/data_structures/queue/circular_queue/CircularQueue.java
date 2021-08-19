package author.wzn.data_structures.queue.circular_queue;


//环形队列
public class CircularQueue {
    private final int[] arr;
    private final int maxSize;
    private int rear = 0;
    private int front = 0;

    public CircularQueue(int maxSize){
        this.maxSize = maxSize + 1;
        this.arr = new int[maxSize + 1] ;
    }

    public int getMaxSize() {
        return maxSize;
    }
    //加入队列
    public void addQueue( int n) {
        if (isFull()) {
            throw new RuntimeException("isFull");
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    //出队列
    public int outQueue() {
        if (isEmpty()) {
            throw new RuntimeException("isEmpty");
        }
        int i = arr[front];
        front = (front + 1) % maxSize;
        return i;
    }

    public void show(){

        int r = rear;
        int f = front;

        while (( r != f)) {
            System.out.println(arr[f]);
            f = (f + 1) % maxSize;
        }
    }

    public boolean isFull() { return (rear + 1) % maxSize == front; }

    public boolean isEmpty(){ return rear == front; }

    public int size() { return (rear + maxSize - front) % maxSize; }

    public static void main(String[] args) {

    }
}
