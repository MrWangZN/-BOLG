package author.wzn.algorithms.practice.offer;


public class Offer_22 {
    public static void main(String[] args) {

        ListNode head = new ListNode(5);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        ListNode node = new ListNode(1);
        head.next.next.next.next = node;
        node.next = head;


        ListNode node1 = detectCycle(head);

    }
    //使用快慢指针,fast的速度时slow的两倍,他们相遇的位置到环的入口点 == head到入环点的距离
    public static ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }
}
