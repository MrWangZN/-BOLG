package author.wzn.algorithms.practice.leetCode;

import java.io.FileInputStream;
import java.io.IOException;

//反转字符串中的元音字母
public class LeetCode_345 {

    public static void main(String[] args) {
        System.out.println(reverseVowels("Ui"));

    }
    public static String reverseVowels(String s) {
        int left =0;
        int right = s.length()-1;
        char[] s1 = s.toCharArray();

        while(left<right){
            while(left<right && !isYuan(s1[left])){
                left++;
            }
            while(left<right && !isYuan(s1[right])){
                right--;
            }
            char temp = s1[left];
            s1[left] = s1[right];
            s1[right] = temp;
            left++;
            right--;
        }
        return String.valueOf(s1);
    }

    public static boolean isYuan(char item){
        return (item == 'a')||item == 'A'||item == 'e'||item == 'E'||item == 'i'||item=='I'
                ||item == 'o'||item =='O'||item == 'u'||item =='U';
    }
}
