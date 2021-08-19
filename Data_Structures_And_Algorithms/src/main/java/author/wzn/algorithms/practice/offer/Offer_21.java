package author.wzn.algorithms.practice.offer;

import java.util.Arrays;

//剑指 Offer 21.
//调整数组顺序使奇数位于偶数前面
public class Offer_21 {

    public static void main(String[] args) {

        int[] nums = {99 ,66 ,40 ,33, 9 ,97 ,90 ,71, 100, 38};

        System.out.println(Arrays.toString(exchange(nums)));
    }


    //AC了   快速排序的思想
    public static int[] exchange(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return nums;
        }
        int left = 0;
        int right = nums.length - 1;
        int point = nums[0];
        while (left < right) {
            while (nums[right] % 2 == 0 && left < right) {
                right--;
            }
            nums[left] = nums[right];
            while (nums[left] % 2 != 0 && left < right) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = point;
        return nums;
    }
}
