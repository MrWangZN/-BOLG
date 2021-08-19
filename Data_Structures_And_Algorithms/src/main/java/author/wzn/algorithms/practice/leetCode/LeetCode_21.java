package author.wzn.algorithms.practice.leetCode;



//21. 合并两个有序链表
public class LeetCode_21 {

    public static void main(String[] args) {

    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {


        ListNode prehead = new ListNode(-1);
        ListNode temp = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                temp.next = l1;
                l1 = l1.next;
            }else{
                temp.next = l2;
                l2 = l2.next;
            }
            temp =temp.next;
        }
        temp.next =  l1 == null ? l2 : l1;
        return prehead.next;
    }
}
 class ListNode {
  int val;
  ListNode next;
  ListNode() {}
  ListNode(int val) { this.val = val; }
  ListNode(int val, ListNode next) { this.val = val; this.next = next;}

     @Override
     public String toString() {
         return "ListNode{" +
                 "val=" + val +
                 ", next=" + next +
                 '}';
     }
 }
