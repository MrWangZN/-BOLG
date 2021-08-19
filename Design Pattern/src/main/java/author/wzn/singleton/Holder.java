package author.wzn.singleton;

/*
*
* @Date: 2021/8/11
* @Description: 静态内部类懒汉单例
*/
public class Holder {

    private Holder(){}

    private static Holder holder;

    public static Holder getInstance() {
        return InnerClass.HOLDER;
    }

    static class InnerClass{
        private static final Holder HOLDER = new Holder();
    }
}