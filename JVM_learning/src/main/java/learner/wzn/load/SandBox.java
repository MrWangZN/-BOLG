package learner.wzn.load;

/**
* @author :
* @Date: 2021/8/22
* @Description: 引导类加载器在加载的过程中会先加载JDK自带的文件，进而保护java核心源代码
*/
public class SandBox {
    public static void main(String[] args) {
        //一节自定义的String，main方法就找不到了。这就是沙箱的保护
    }
//    static class String{
//
//    }
}
