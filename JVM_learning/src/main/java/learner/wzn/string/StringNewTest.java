package learner.wzn.string;

/**
 * @Author: 王振南
 * @Date: 2021/8/26
 * @Description: String s = new String("s");一共new了几个对象？2个
 *               怎么证明：查看字节码指令！
 */
public class StringNewTest {

    public static void main(String[] args) {

        /*
        *  0 new #2 <java/lang/String>
           3 dup
           4 ldc #3 <s>
           6 invokespecial #4 <java/lang/String.<init> : (Ljava/lang/String;)V>
           9 astore_1
          10 return
        *
        *
        * 0 new #2 <java/lang/String>  new了对象
        * 4 ldc #3 <s>                 在常量池中存放了<s>
        * */
        String s = new String("s");


        //那以下呢？
        /*
          对象1、StringBuilder
          对象2、new String
          对象3、new String
          对象4、常量池中的ss (第二个ss，是使用的字符串常量池中的ss)
          对象5、toString--->相当于new String("ssss")（但是字符串常量池不会存在“ssss”）
          ps: 这个时候字符串常量池中不存在"ssss"，可以去看StringBuilder.toString方法 ， 可以去看StringBuilder.toString()在字符串常量池中生成"ssss"
        * */
        String s1 = new String("ss") + new String("ss");
        /*
        *  new #5 <java/lang/StringBuilder>
           13 dup
           14 invokespecial #6 <java/lang/StringBuilder.<init> : ()V>
           17 new #2 <java/lang/String>
           20 dup
           21 ldc #7 <ss>
           23 invokespecial #4 <java/lang/String.<init> : (Ljava/lang/String;)V>
           26 invokevirtual #8 <java/lang/StringBuilder.append : (Ljava/lang/String;)Ljava/lang/StringBuilder;>
           29 new #2 <java/lang/String>
           32 dup
           33 ldc #7 <ss>
           35 invokespecial #4 <java/lang/String.<init> : (Ljava/lang/String;)V>
           38 invokevirtual #8 <java/lang/StringBuilder.append : (Ljava/lang/String;)Ljava/lang/StringBuilder;>
           41 invokevirtual #9 <java/lang/StringBuilder.toString : ()Ljava/lang/String;>
        * */
    }
}
