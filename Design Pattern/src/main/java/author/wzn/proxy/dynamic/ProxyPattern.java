package author.wzn.proxy.dynamic;


import author.wzn.proxy.stat1c.Defendant;
import author.wzn.proxy.stat1c.People;
import author.wzn.proxy.stat1c.Plaintiff;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyPattern {

    public static void main(String[] args) {
        Plaintiff plaintiff = new Plaintiff();
        Defendant defendant = new Defendant();
        People plaintiffProxy = (People) new Lawyer(plaintiff).newProxyInstance();
        People defendantProxy = (People) new Lawyer(defendant).newProxyInstance();
        plaintiffProxy.speak();
        defendantProxy.speak();
    }
}



class Lawyer implements InvocationHandler {

    private final Object object;
    public Lawyer(Object object) {
        this.object = object;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("法官");
        method.invoke(object, args);
        System.out.println("我说完了");
        return null;
    }
    public Object newProxyInstance(){
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), this);
    }
}

