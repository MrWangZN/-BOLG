package author.wzn.algorithms.practice.leetCode;

import java.util.ArrayList;

//位1的个数
public class LeetCode_191 {
    public static void main(String[] args) {



    }

    public static int hammingWeight(int n) {
        char[] chars = Integer.toBinaryString(n).toCharArray();
        int counts = 0;
        for(char item : chars){
            if(item == '1'){
                counts++;
            }
        }
        return counts;
        //return Integer.bitCount(n);  使用函数库的方式 只要一句话
    }

}
