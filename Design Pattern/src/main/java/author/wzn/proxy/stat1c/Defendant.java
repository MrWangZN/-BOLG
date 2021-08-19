package author.wzn.proxy.stat1c;


//被告
public class Defendant implements People{
    @Override
    public void speak(){
        System.out.println("他骗人");
    }
}
