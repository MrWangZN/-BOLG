package author.wzn.algorithms.practice.leetCode;


//字符串匹配
public class LeetCode_28 {


    public static void main(String[] args) {
        int i = strStr_2("a", "a");
        System.out.println(i);
    }

    public static int strStr_1(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int i  = 0;
        int j  = 0;
        while (i < s1.length && j < s2.length) {
            if (s1[i] == s2[j]) {
                j++;
                i++;
            }else{
                i = i -j +1;
                j = 0;
            }
        }
        if (j == s2.length) {
            return i - j;
        }
        return -1;
    }
    public static int strStr_2(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int i  = 0;
        int j  = 0;
        int[] tab = tab(str2);
        while (i < s1.length && j<s2.length) {

            while (j > 0 && s2[j] != s1[i]){
                j = tab[j - 1];
            }
            if (s2[j] == s1[i]) {
                j++;
            }
            i++;
        }
        if (j == s2.length) {
            return i - j;
        }

        return -1;
    }

    public static int[] tab(String str2) {
        int length = str2.length();
        int[] tab = new int[length];
        int i =0;
        int j =0;
        if (length > 0) {
            tab[i++] = 0; //第一个数字时0;
            while (i < length) {
                while(j>0 && str2.charAt(i) != str2.charAt(j)){
                    j = tab[j - 1];
                }

                if (str2.charAt(i) == str2.charAt(j)) {
                    j++;
                }
                tab[i] = j;
                i++;
            }
        }
        return tab;
    }


}
