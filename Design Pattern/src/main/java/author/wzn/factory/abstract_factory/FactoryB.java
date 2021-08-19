package author.wzn.factory.abstract_factory;

public class FactoryB implements AbstractFactory {

    String data = "";

    @Override
    public Product getProduct() {
        return new ProductB();
    }

    private static class ProductB implements Product {
        @Override
        public void function() {
            String temp = data;
        }
    }
}
