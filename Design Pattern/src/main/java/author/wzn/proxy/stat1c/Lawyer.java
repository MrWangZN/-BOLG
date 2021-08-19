package author.wzn.proxy.stat1c;


//律师
// 优点类似装饰器模式了，装饰器模式的重点在于扩展，而代理模式的重点在于代理而不是扩展
public class Lawyer implements People{
    private People proxy;
    public Lawyer(People people){
        this.proxy = people;
    }
    public Lawyer(){
    }
    public void speak(){
        if (proxy != null) {
            System.out.println("法官");
            proxy.speak();
            System.out.println("我说完了");
        }
    }
    public void setProxy(People proxy) {
        this.proxy = proxy;
    }
}