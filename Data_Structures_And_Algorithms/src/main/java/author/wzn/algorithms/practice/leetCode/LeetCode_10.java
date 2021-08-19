package author.wzn.algorithms.practice.leetCode;


// Offer - 10 : 斐波那契数列
public class LeetCode_10 {

    public static void main(String[] args) {
        for (int i = 0; i < 100 ; i++) {
                System.out.println((fib(i)==fib_2(i))+"     "+ fib(i) + "      " + fib_2(i)   + "     第"+ i +"个");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
//  0 <= n <= 100
    public static int fib( int n) {
        int a = 0, b = 1, sum = 0;
        for (int i = 1; i < n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }
    //使用递归的方式
    public static int fib_2(int n) {
        if (n <= 1) {
            return 0;
        }else if( n == 2){
            return n -1;
        }
        return getNum(0, 1,3, n);
    }

    public static int getNum(int a, int b, int cur,int n) {
        if (cur == n) {
            return (a+b)%1000000007;
        }
        return getNum(b, (a + b) % 1000000007, cur + 1, n);
    }
}
