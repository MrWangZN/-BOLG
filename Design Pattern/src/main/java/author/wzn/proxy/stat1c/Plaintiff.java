package author.wzn.proxy.stat1c;


//原告
public class Plaintiff implements People{
    @Override
    public void speak() {
        System.out.println("他搞我");
    }
}
