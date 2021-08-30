package author.wzn.fly_weight;

import java.util.HashSet;
import java.util.Set;

/*
 *
 * @Date: 2021/8/19
 * @Description: 享元模式 - 使用共享技术有效的支持大量细粒度的对象
 *       享元模式由四部分组成:
 *           * 抽象享元
 *           * 具体享元
 *           * 享元工厂
 *           * 客户端
 *       享元包含内部状态和外部状态（同时共享）
 *       内部状态是不变的,外部状态受客户端传入影响
 *
 * 例子: string 常量池,包装类常量池...
 *      线程池、连接池等
 * 演示: 共享单车
 */
public class FlyWeightPattern {
    public static void main(String[] args) {
        SharedBicyclesFactory factory = new SharedBicyclesFactory(5);
        SharedBicycle bike = factory.getBike();
        bike.take("zs");
        bike.back();
        System.out.println(factory.freeCounts());
    }
}


