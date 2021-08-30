package learner.wzn.string;

public class StringTest {
    public static void main(String[] args) {
        //测试字符串拼接左右两边都是字符串常量
        test1();
        //测试字符串拼接左右两边都是常量引用
        test2();

    }
    /**
     *  字符串拼接如果符号左右边都是字符串常量或者常量引用，则使用编译期优化。！
     *  针对变量使用StringBuild! 新生成的对象放在堆中，而不是常量池中。可以使用intern（）;
    * */
    public static void test1(){

        String s1 = "javaEE";

        String s2 = "hadoop";

        String s3 = "javaEEhadoop";
        //编译器优化直接生成javaEEhadoop
        String s4 = "javaEE" + "hadoop";
        //变量拼接则“相当于” new String()（实际使用了StringBuild然后toString）
        String s5 = "javaEE" + s2;

        String s6 = s1 + "hadoop";

        String s7 = s1 + s2;

        System.out.println(s3 == s4);//true
        System.out.println(s3 == s5);//false
        System.out.println(s3 == s6);//false
        System.out.println(s3 == s7);//false

        System.out.println(s7.intern() == s3);//true
    }
    public static void test2(){

        final String s1 = "javaEE";
        final String s2 = "hadoop";
        String s3 = "javaEEhadoop";

        String s4 = s1 + s2;

        System.out.println(s3 == s4);//true
    }
}

