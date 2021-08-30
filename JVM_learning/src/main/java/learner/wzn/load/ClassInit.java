package learner.wzn.load;



public class ClassInit {

    private static int a = 3;

    //初始化阶段就是执行类构造器方法<clinit>()的过程
    //此方法不需定义，是javac编译器自动收集类中的所有类变量的赋值动作和静态代码块中的语句合并而来。
    //也就是说，当我们代码中包含static变量的时候，就会有clinit方法
    //<clinit>()方法中的指令按语句在源文件中出现的顺序执行 , 这也是为什么b最后还是5。
    static{
        a = 4;
        b = 4;
        System.out.println(a);
        //System.out.println(b);//非法前向引用
    }

    private static int b = 5;

    public static void main(String[] args) {
        a_b();
    }

    //打印a,b的值
    public static void a_b(){
        System.out.println(a); //4
        System.out.println(b); //5     静态代码块可以读取到b是因为,在赋值之前的,prepare阶段静态变量被赋值为默认值。
                                    // 在初始化就才赋值,然后 b = 0 -> 4 ->5
    }
}
