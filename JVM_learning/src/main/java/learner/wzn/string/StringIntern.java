package learner.wzn.string;

/**
 * @Author: 王振南
 * @Date: 2021/8/26
 * @Description: 如何保证变量s指向的常量池中的数据呢？
 * 方式一 String  s = "sss";
 * 方式二 使用intern();
 * new String("sss").intern();
 * new StringBuild("sss").intern();
 * <p>
 *        intern()的方法在不同版本的操作有一些差别见底下方法
 *
 *        对于程序中大量存在的字符串，尤其其中存在很多重复的字符串时，使用intern()可以节省内存空间。
 */
public class StringIntern {
    public static void main(String[] args) {
        faceTest1();
        faceTest2();
        faceTest3();
    }

    private static void faceTest1() {
        String s1 = new String("1");
        s1.intern();//试图把"1"放在常量池中，但是"1"中已经存在于字符串常量池中

        String s2 = "1";
        //JKD /6/7/8 false
        //s1 是new的对象,指向了堆中的对象 s2是常量池中的s2
        System.out.println(s1 == s2);

        String s3 = new String("1") + new String("1");
        s3.intern();//首先确定"11",没有在字符串常量池中。其次调用intern()方法,在1.6的时候,是先常量池中添加了"11"
        //1.7之后,字符串常量池表示"11"的值，是指向堆中s3的实例变量的。
        //因此在1.6是false的,1.7、1.8是true
        String s4 = "11";
        //JKD /6 false 7/8 true
        System.out.println(s3 == s4);
        //请继续看faceTest2();
    }
    private static void faceTest2(){
        String s3 = new String("1") + new String("1");
        String s4 = "11";
        s3.intern();
        //是否相等？//false
        System.out.println(s3 == s4);
    }

    /**
     *
     * 继续练习
     *
    * */
    private static void faceTest3(){
//        String s = new String("ab");//分别是什么情况呢
        String s = new String("a") + new String("b");
        s.intern();
        String s1 = "ab";
        System.out.println(s == s1);
    }

}
