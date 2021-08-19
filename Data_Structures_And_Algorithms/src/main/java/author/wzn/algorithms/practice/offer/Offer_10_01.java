package author.wzn.algorithms.practice.offer;

//Offer 10- I. 斐波那契数列
public class Offer_10_01 {

    public static void main(String[] args) {
        System.out.println(fib(7));
        System.out.println(fib_2(7));
    }

    public static int fib(int n) {
        if (n <= 1) {
            return n;
        }
        int sum = 0;
        int a = 0;
        int b = 1;
        for (int i = 0; i < n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }

    //递归
    public static int fib_2(int n) {
        if (n <= 1) {
            return n;
        }
        return getNum(0, 1, 0, n);
    }

    public static int getNum(int first, int second, int index, int n) {
        if (index == n) {
            return first;
        }
        return  getNum(second, (first + second) % 1000000007, ++index, n);
    }
}