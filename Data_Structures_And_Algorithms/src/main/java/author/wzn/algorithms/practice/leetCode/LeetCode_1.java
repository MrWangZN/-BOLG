package author.wzn.algorithms.practice.leetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//两数之和
public class LeetCode_1 {

    public static void main(String[] args) {
        int[] array = {2, 7, 11, 15, 13};
        int[] answer = twoSum(array, 13);
        System.out.println(Arrays.toString(answer));
    }

    public static int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        int other;//需要的另外一个数字
        for (int i = 0; i < nums.length; i++) {

            other = target - nums[i];
            Integer integer = map.get(other);
            if (integer != null) {
                return new int[]{integer, i};
            }
            map.put(nums[i], i);
        }
        return null;

    }
}
