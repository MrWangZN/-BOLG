package author.wzn.algorithms.practice.offer;

//删除链表的倒数第 n 个结点   第二版
public class Offer_21_02 {
    public static void main(String[] args) {

    }
    //AC了,但是判断条件有点多 后面看一看怎么优化 速度是对的 100
    //题目默认了结点数量最少为1
    public static ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode curr = head;
        int size = 0;
        while (curr != null) {
            curr = curr.next;
            size++;
        }
        if(n == size){
           return head.next;
        }
        ListNode head1 = head;
        if (head != null) {
            curr= head;
            head1 = head1.next;
            for (int i = 1; i < size - n; i++) {
                curr = curr.next;
                head1 = head1.next;
            }
            curr.next = head1.next;
        }
        return head;
    }
}
