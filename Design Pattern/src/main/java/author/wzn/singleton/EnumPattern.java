package author.wzn.singleton;

/*
*
* @Date: 2021/8/19
* @Description: 单例模式 - 枚举 能够避免反射创建实例等问题
*/
public enum EnumPattern {

    INSTANCE;

    private  final  Singleton instance;

    EnumPattern(){
        instance = new Singleton("");
    }

    public Singleton getInstance() {
        return INSTANCE.instance;
    }
    //设计成为内部类而不是静态内部类避免他人访问
    static class Singleton{
        Singleton(String name) {
            this.name = name;
        }
        private String name;

        public void setName(String name) {
            this.name = name;
        }
    }

}
