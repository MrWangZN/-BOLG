package author.wzn.algorithms.practice.leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode_15 {
    public static void main(String[] args) {
        List<List<Integer>> lists = threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        System.out.println(lists);
    }

    public static List<List<Integer>> threeSum(int[] nums) {
//[-4, -1, -1, 0, 1, 2]
        List<List<Integer>> list =new ArrayList<>();
        //对数组进行排序
        if (nums == null || nums.length == 0) {
            return list;
        }
        Arrays.sort(nums);
        if (nums[0] > 0) {
            return list;
        }
        int target;
        int firstIndex;
        int thirdIndex;
        for (int i = 0; i < nums.length-2; i++) {
            firstIndex = i;
            if (nums[i] > 0) {
                return list;
            }
//            target = -nums[i];
            for (int i1 = i + 1; i1 < nums.length-1; i1++) {
                target = -nums[i] - nums[i1];
                if(i1 + 1 <= nums.length - 1 ){
                    thirdIndex = Arrays.binarySearch(nums, i1+1, nums.length, target);
                    if (thirdIndex >= 0) {
                        List<Integer> arrayList = new ArrayList<>();
                        arrayList.add(nums[firstIndex]);
                        arrayList.add(nums[i1]);
                        arrayList.add(nums[thirdIndex]);
                        list.add(arrayList);
//                        System.out.printf("%d---->%d---->%d", firstIndex, i1, thirdIndex);
                    }
//                    System.out.println();
                }
                while (i1+1< nums.length && nums[i1] == nums[i1+1]) {
                    i1++;
                }
            }
            while (i+1< nums.length && nums[i] == nums[i+1]) {
                i++;
            }
        }
        return list;
    }
}
