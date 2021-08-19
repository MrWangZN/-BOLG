package author.wzn.algorithms.practice.offer;

//替换空格
public class Offer_5 {

    public static void main(String[] args) {
        replaceSpace("We are happy."); //方法一
        replaceSpace_2("We are happy.");//使用API
    }

    public static String replaceSpace(String s) {

        char[] chars = s.toCharArray();
        char[] chars1 = new char[s.length() * 3];

        int size = 0;
        for (char aChar : chars) {

            if (aChar == ' ') {
                chars1[size++] = '%';
                chars1[size++] = '2';
                chars1[size++] = '0';
            } else {
                chars1[size++] = aChar;
            }
        }
        return String.valueOf(chars1, 0, size);
    }

    public static String replaceSpace_2(String s) {
        return s.replaceAll(" ", "%20");
    }
}