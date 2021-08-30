package author.wzn.algorithms.practice.leetCode;
//24. 两两交换链表中的节点
public class LeetCode_24 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode head2 = new ListNode(2);
        head.next = head2;
        ListNode head3 = new ListNode(3);
        head2.next = head3;
        ListNode head4 = new ListNode(4);
        head3.next = head4;
        ListNode head5 = new ListNode(5);
        head4.next = head5;
        System.out.println(swapPairs(head));
    }
    public static ListNode swapPairs(ListNode head) {
        if(head == null) return null;
        else if(head.next == null){
            return head;
        }
        ListNode curr = head;
        ListNode next;
        ListNode pre = null;
        ListNode ans = head.next;
        while(curr != null && curr.next != null){
            next = curr.next;
            curr.next = next.next;
            next.next = curr;
            if(pre!=null){
                pre.next = next;
            }
            pre = curr;
            curr = curr.next;
        }
        return ans;
    }
}
