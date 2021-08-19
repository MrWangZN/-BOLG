package author.wzn.factory.simple_factory;


/*
 * 简单工厂模式
 *       优点：屏蔽了对象的创建，根据参数不同返回不同类型的对象 创建对象的具体细节完全交给工厂 ，客户端程序员只需要使用就好
 *
 *       缺点：扩展性差，如果产品数量增加就要扩展业务逻辑，怎加复杂度;
 * */
//举一个例子：DateFormat的get方法
public class SimpleFactory implements SimpleFactoryService {

    private static final String A = "A";
    private static final String B = "B";

    @Override
    public Product product(String productName) {

        if (A.equals(productName)){
            return new ProductA();
        }else{
            return new ProductB();
        }
    }
}
