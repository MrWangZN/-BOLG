package author.wzn.algorithms.KMP;

/*
* leetCode 26
*
* 使用kmp算法匹配字符串
* */
public class KMPAlgorithm {

    public static void main(String[] args) {
        int index = "BBC ABCDAB ABCDABCDABDE".indexOf("ABCDABD"); //api库偷懒
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        System.out.println("strStr= " + strStr(str1, str2));
    }

//  int[] next 字符串匹配表
    public static int strStr(String str1, String str2) {
        int s1_length = str1.length();
        int s2_length = str2.length();
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int i = 0;
        int j = 0;

        int[] next = kmpNext(str2);

        while (i < s1_length  && j< s2_length) {
            //  KMP算法的核心
            while (j > 0 && s1[i] != s2[j]){
                j = next[j - 1];
            }

            if (s1[i] == s2[j]) {
                j++;
            }
            i++;
        }
        if (j == s2_length) {
            return i - j;
        }
        return -1;
    }

    public static int[] kmpNext(String str) {
        int length = str.length();
        int[] next = new int[length];
        int i = 0;
        int j = 0;
        if (length > 0) {
            next[i++] = 0;
            while (i < length) {
                while (j > 0 && str.charAt(i) != str.charAt(j)){
                    j = next[j - 1];
                }
                if (str.charAt(i) == str.charAt(j)) {
                    j++;
                }
                next[i] = j;
                i++;
            }
        }
        return next;
    }
}
