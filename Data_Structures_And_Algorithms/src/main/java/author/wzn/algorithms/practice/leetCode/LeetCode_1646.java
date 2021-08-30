package author.wzn.algorithms.practice.leetCode;

/**
 * @Author: 王振南
 * @Date: 2021/8/23
 * @Description: 获取生成数组中的最大值
 */
public class LeetCode_1646 {
    public static void main(String[] args) {
        System.out.println(getMaximumGenerated(15));
    }
    /**
     *
     * 100% 直接ac
     *
     */
    public static int getMaximumGenerated(int n) {
        if( n<=1 ){
            return n;
        }
        int max = 1;
        int[] nums = new int[n+1];
        nums[0] = 0;
        nums[1] = 1;
        for(int i = 2;i <=n; i++){
            if( i%2==0 ){
                nums[i] = nums[i/2];
                max = Math.max(max, nums[i]);
            }else{
                nums[i] = nums[i/2] + nums[i/2+1];
                max = Math.max(max, nums[i]);
            }
        }
        return max;
    }
}
