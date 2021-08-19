package author.wzn.algorithms.practice.offer;

//这是箭指Offer第一版中的22题,另外一个是第二版中的22题
//链表中倒数第k个节点
public class Offer_22_01 {
    public static void main(String[] args) {

    }


    public static ListNode getKthFromEnd(ListNode head, int k) {
        ListNode node = head;
        int size = 0;
        while (node != null) {
            size++;
            node = node.next;
        }
        if (head != null) {
            for (int i = 1; i < size - k + 1; i++) { //从一开始计数
                head = head.next;
            }
        }
        return head;
    }
}
