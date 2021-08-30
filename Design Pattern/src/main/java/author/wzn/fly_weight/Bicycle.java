package author.wzn.fly_weight;

abstract class Bicycle {
    protected int state; //内部使用
    protected String userName;

    abstract void take(String useName);
    abstract void back();
}


