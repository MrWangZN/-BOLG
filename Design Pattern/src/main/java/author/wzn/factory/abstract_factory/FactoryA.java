package author.wzn.factory.abstract_factory;



public class FactoryA implements AbstractFactory{

    String data;

    @Override
    public Product getProduct() {
        return new ProductA();
    }
    //产品私有化
    private static class ProductA implements Product {

        String temp;

        @Override
        public void function() {
            temp = data;
        }
    }
}
