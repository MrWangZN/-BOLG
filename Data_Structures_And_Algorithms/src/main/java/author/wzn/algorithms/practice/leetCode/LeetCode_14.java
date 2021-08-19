package author.wzn.algorithms.practice.leetCode;

//14. 最长公共前缀
public class LeetCode_14 {


    public static void main(String[] args) {

        String prefix = longestCommonPrefix(new String[]{"flower", "flow", "flight"});
        System.out.println(prefix);

    }


    public static String longestCommonPrefix(String[] strs) {
        //获得最短的字符串长度
        int length = strs[0].length();
        for (String str : strs) {
            if (str.length() == 0) {
                return "";
            } else if (str.length() < length) {
                length = str.length();
            }
        }
        int index = 0;
        String s = strs[0];
        w1:while (index < length) {
            char c = s.charAt(index);
            for (String str : strs) {
                if (c != str.charAt(index)) {
                    break w1;
                }
            }
            index++;
        }
        return s.substring(0, index);
    }

}
