package author.wzn.singleton;

import java.io.Serializable;

/*
 *
 * @Date: 2021/8/10
 * @Description: 饿汉单例模式  - 饿汉式单例类.在类初始化时，已经自行实例化
 */
public class HungrySingleton implements Serializable {


    private static final HungrySingleton instance = new HungrySingleton();


    public static HungrySingleton getInstance() {
        return instance;
    }


    //如果实现了序列化接口,还要编写以下方法来防止反序列化破话单例
    //反序列化过程中,发现readResolve返回了对象,就会采用你返回的对象,而不是反序列生成的对象
    public Object readResolve(){
        return instance;
    }

}
