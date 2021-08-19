package author.wzn.algorithms.fibonacci;


//斐波那契数列
public class Fibonacci {
    public static void main(String[] args) {

        System.out.println(fib(1 )% 1000000007);

    }

    public static long fib(int n) {
        int a = 0 ;
        int b = 1;
        int sum =0;
        for (int i = 1; i < n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }
}
