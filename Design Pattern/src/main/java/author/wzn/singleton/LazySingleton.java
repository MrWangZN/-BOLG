package author.wzn.singleton;
/*
*
* @Date: 2021/8/10
* @Description: 懒汉模式，在需要的时候在初始化对象。代码简单但是里面关于synchronized、volatile、dcl(double check locking)值得深思
*/
public class LazySingleton {

    private static volatile LazySingleton instance;

    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if(instance == null) {
                    instance = new LazySingleton();
                }
            }
        }

        return instance;
    }

}
