package author.wzn.algorithms.practice.leetCode;

import java.util.Stack;

//7. 整数反转
public class LeetCode_7 {

    public static void main(String[] args) {
        System.out.println(reverse(-34));

    }



    public static int reverse(int x) {
        char[] chars =(x + "").toCharArray();
        int index = 0;
        if (x < 0) {
            index++;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = index; i < chars.length; i++) {
            stack.push(chars[i]);
        }
        StringBuilder s = new StringBuilder();
        while(!stack.isEmpty()){
            s.append(stack.pop());
        }

        try{
            return (x < 0) ? Integer.parseInt(("-" + s)) : Integer.parseInt((s.toString()));
        }catch (NumberFormatException e) {
            return 0;
        }
    }

    public int reverse22(int x) {
        int rev = 0;
        while (x != 0) {
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
                return 0;
            }
            int digit = x % 10;
            x /= 10;
            rev = rev * 10 + digit;
        }
        return rev;
    }
}
