package author.wzn.algorithms.KMP;

/*
*
* leetCode 26
*
* 使用暴力匹配字符串
*
* */
//不考虑null-String 无意义
public class ViolenceMatch {

    public static void main(String[] args) {
        System.out.println("violenceMatch = " + violenceMatch("", ""));
    }
    //两种暴力实现方式一、
    public static int violenceMatch(String str1, String str2) {

        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int i  = 0;
        int j  = 0;
        while (i < s1.length && j<s2.length) {
            if (s1[i] == s2[j]) {
                i++;
                j++;
            }else{
                i = i - j + 1;
                j = 0;
            }
        }
        //如果是s2空串 就直接返回  极少情况s2.length == 0 ,就不用一开始就判断是否s2.length == 0，更多情况下不是，就放在后面考虑。优化
        if (j == s2.length) {
            return i - j;
        }
        return -1;
    }


    //实现方式一、
    public static int strStr(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        for (int i = 0; i + m <= n; i++) {
            boolean flag = true;
            for (int j = 0; j < m; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }

}
