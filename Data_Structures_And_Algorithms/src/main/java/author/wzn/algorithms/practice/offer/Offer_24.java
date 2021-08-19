package author.wzn.algorithms.practice.offer;

import java.util.Stack;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//链表反转
//实现方法一:使用栈的方式实现
public class Offer_24 {

    public static void main(String[] args) {
        ListNode head = new ListNode(5);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(1);

//        ListNode listNode = reverseList_1(head);

        ListNode node = reverseList_3(head);
        System.out.println(node);
    }


    public static ListNode reverseList_1(ListNode head) {

        Stack<ListNode> stack = new Stack<>();

        while (head != null) {
            stack.push(head);
            head = head.next;
        }

        if (stack.size()== 0) {
            return null;
        }

        ListNode node = stack.pop();
        ListNode temp = node;

        while (!stack.isEmpty()) {
            temp.next = stack.pop();
            temp = temp.next;
        }
        temp.next = null;
        return node;
    }

    //能够使用栈就能够用递归
    public static ListNode reverseList_2(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverseList_2(head.next);
        head.next.next = head;
        head.next = null;

        return newHead;
    }

    public static ListNode reverseList_3(ListNode head)  {

        ListNode newNode= null;

        ListNode curr = head;
        ListNode next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = newNode;
            newNode = curr;
            curr = next;
        }
        return newNode;

    }
}
class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}