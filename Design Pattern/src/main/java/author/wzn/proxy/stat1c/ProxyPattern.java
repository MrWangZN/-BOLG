package author.wzn.proxy.stat1c;


//代理模式 - 静态代理
public class ProxyPattern {

    public static void main(String[] args) {
        People plaintiff = new Plaintiff();
        People defendant = new Defendant();
        Lawyer p_lawyer = new Lawyer(plaintiff);
        Lawyer d_lawyer = new Lawyer(defendant);
        System.out.println("原告律师你有什么想说的");
        p_lawyer.speak();
        System.out.println("被告律师你有什么想说的");
        d_lawyer.speak();
    }
}
