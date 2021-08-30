package author.wzn.algorithms.practice.leetCode;



/*
*
* @Date: 2021/8/21
* @Description: 力扣541题,反转字符串 II
*/
public class LeetCode_541 {
    public static void main(String[] args) {
        System.out.println(reverseStr("abcdefg", 2));
    }
    //击败93.16%
    public static String reverseStr(String s, int k) {
        int length = s.length();
        if(length == 1 || k == 1){
            return s;
        }
        int lengthCount = length;
        char[] s1 =  s.toCharArray();
        int left = 0;
        int time = 1;
        int right;
        int push;
        char temp;
        while(lengthCount > 0){
            if(lengthCount < 2*k){
                push = left;
                right = lengthCount >= k ? time * k - 1 : length - 1;
                while(push<right){
                    temp = s1[right];
                    s1[right--] = s1[push];
                    s1[push++] =temp;
                }
                return String.valueOf(s1);
            }
            else{
                push = left;
                right = left + k -1;
                while(push<right){
                    temp = s1[right];
                    s1[right--] = s1[push];
                    s1[push++] =temp;
                }
                left+=2*k;
                lengthCount = length - left;
            }
            time+=2;
        }
        return String.valueOf(s1);
    }
}
