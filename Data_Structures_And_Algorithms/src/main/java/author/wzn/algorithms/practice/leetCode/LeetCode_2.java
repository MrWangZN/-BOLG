package author.wzn.algorithms.practice.leetCode;



public class LeetCode_2 {
    public static void main(String[] args) {

        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        ListNode listNode = addTwoNumbers_2(l1, l2);
        System.out.println(listNode.toString());


    }

//  时间 3ms 12.61%  内存38.2  98.10%
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int temp = 0;
        boolean isMore = false;
        ListNode list = l1;
        ListNode curr = list;
        while(l1.next!=null && l2.next!=null){
            temp = l1.val + l2.val;
            if (isMore) {
                temp = temp + 1;
            }
            if (temp >= 10) {
                temp = temp% 10;
                isMore = true;
            }else{
                isMore = false;
            }
            curr.val = temp;
//            curr = curr.next = new ListNode();  见这个代码换成下面 速度就变成了97.48%
            curr = curr.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        if(l1!=null &&l2!=null){
            temp = l1.val + l2.val;
            if (isMore) {
                temp = temp + 1;
            }
            if (temp >= 10) {
                temp = temp% 10;
                isMore = true;
            }else{
                isMore = false;
            }
            curr.val = temp;

            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            temp = l1.val;
            if(isMore){
                temp = temp + 1;
            }
            if (temp >= 10) {
                temp = temp% 10;
                isMore = true;
            }else{
                isMore = false;
            }
            curr =curr.next = new ListNode(temp);

            l1 = l1.next;
        }
        while (l2 != null) {
            temp = l2.val;
            if(isMore){
                temp = temp + 1;
            }
            if (temp >= 10) {
                temp = temp % 10;
                isMore = true;
            }else{
                isMore = false;
            }
            curr.next = new ListNode(temp);
            l2 = l2.next;
        }
        if (isMore) {
            curr.next = new ListNode(1);
        }
        return list;
    }
    //速度就变成了97.48%  内存 89.78%
    public static ListNode addTwoNumbers_2(ListNode l1, ListNode l2) {
        int temp;
        boolean isMore = false;
        ListNode list = l1;
        ListNode curr = list;
        while(l1!=null || l2!=null){
            temp = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val);
            if (isMore) {
                temp = temp + 1;
            }
            if (temp >= 10) {
                temp = temp% 10;
                isMore = true;
            }else{
                isMore = false;
            }
            curr.val = temp;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
            if (curr.next == null) {
                curr.next = isMore? new ListNode()  : l2 ;
            }
            curr = curr.next;
        }
        if (isMore) {
            curr.val = 1;
        }
        return list;
    }
}


