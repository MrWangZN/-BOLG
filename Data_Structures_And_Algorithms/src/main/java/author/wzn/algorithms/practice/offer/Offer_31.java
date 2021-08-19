package author.wzn.algorithms.practice.offer;

import java.util.Stack;

/*
 *
 * 栈的压入、弹出序列
 */
public class Offer_31 {
    public static void main(String[] args) {
        System.out.println(validateStackSequences(new int[]{4,0,3,1,2}, new int[]{3,1,0,2,4}));
    }

    //使用一个辅助栈
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int pop = 0;
        for (int j : pushed) {
            stack.push(j);
            while (!stack.isEmpty() && stack.lastElement() == popped[pop]) {
                stack.pop();
                pop++;
            }
        }
        
        return stack.isEmpty();
    }
}
