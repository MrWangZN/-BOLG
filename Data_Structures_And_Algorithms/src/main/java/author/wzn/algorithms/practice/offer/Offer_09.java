package author.wzn.algorithms.practice.offer;

import java.util.Stack;


//用两个栈实现队列
public class Offer_09 {


    public static void main(String[] args) {
        Offer_09 offer_09 = new Offer_09();
        offer_09.push(10);
        offer_09.push(9);
        System.out.println(offer_09.pop());
        offer_09.push(8);
        offer_09.push(6);
        offer_09.push(4);

        System.out.println(offer_09.pop());
        System.out.println(offer_09.pop());
        System.out.println(offer_09.pop());
        System.out.println(offer_09.pop());
        System.out.println(offer_09.pop());
    }

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {

        stack1.push(node);

    }

    public int pop() {
        if(!stack2.isEmpty())
            return stack2.pop();

        if (!stack1.isEmpty()) {

            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        }else{
            return -1;
        }
    }
}
