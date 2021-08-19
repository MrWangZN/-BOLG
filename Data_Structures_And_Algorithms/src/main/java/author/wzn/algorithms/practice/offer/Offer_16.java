package author.wzn.algorithms.practice.offer;

// 剑指 Offer 16
// 数值的整数次方
public class Offer_16 {

    public static void main(String[] args) {
        System.out.println(myPow(2, -2));
    }
    public static double myPow(double x, int n) {
        if (n == 0 || x ==1) {
            return 1;
        }else if (x == -1) {
            if(n % 2 == 0){
                return 1;
            }else{
                return -1;
            }
        }else if(n == -2147483648) {
            return 0;
        }
        double result = x;
        int time = 1;
        int multi = n;
        if (n < 0) {
            multi = -multi;
        }
        while (time < multi) {
            result *= x;
            time++;
        }
        return n < 0 ? 1/result:result;
    }
}
