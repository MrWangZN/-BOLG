package learner.wzn.load;

/*
 *
 * @Date: 2021/8/22
 * @Description: 三个主要的类加载器。
 *               引导（启动）类加载器(BootstrapClassLoader)、扩展类加载器(ExtClassLoader)、应用类加载器(AppClassLoader)
 *               当然还可以自定义类加载器
 *
 *      ps : BootstrapClassLoader的打印为null,因为其由c\c++实现。嵌套在JVM内部。加载rt.jar、resources.jar、sun.boot.class.path路径下的内容
 *           ExtClassLoader是java语言编写（以下程序可以验证）, 父类是BootstrapClassLoader,加载ext下的子目录的内容（如果将我们创建的jar放在这个目录下也自动由此加载）、java.ext.dir系统属性所指定的目录中加载类库
 *           AppClassLoader是java语言编写（以下程序可以验证）, 父类是ExtClassLoader,是程序中默认的类加载器,我们编写的类有其加载（除非以上）
 *
 *      ps : 这三个类加载器, 并不是父子等关系,如果要理解,可以解释为等级不同。（class本就有阶级的意思）
 */

import sun.misc.Launcher;

import java.net.URL;

public class ClassLoader_Test {
    public static void main(String[] args) throws Exception{
        //获取自己编写的类加载器。
        ClassLoader appClassLoader = ClassLoader_Test.class.getClassLoader();
        System.out.println(appClassLoader);//sun.misc.Launcher$AppClassLoader@18b4aac2

        //获取系统默认类加载器
        System.out.println(ClassLoader.getSystemClassLoader());//sun.misc.Launcher$AppClassLoader@18b4aac2

        //获取扩展类加载器
        ClassLoader extClassLoader = ClassLoader.getSystemClassLoader().getParent();
        System.out.println(extClassLoader);//sun.misc.Launcher$ExtClassLoader@4554617c

        //ClassLoader的类加载器 - > BootstrapClassLoader
        System.out.println(ClassLoader.class.getClassLoader());//null

        //加载String的类加载器
        System.out.println(String.class.getClassLoader());//null
        //desc : 为了保证系统的安全,使用引导类加载器加载String类,具体涉及到双亲委派

        bootstrap_loaderUrls();
        ext_loaderUrls();
        getClassLoader();
    }


    //获取BootstrapClassLoader的加载路径
    public static void bootstrap_loaderUrls(){
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();

        for (URL urL : urLs) {
            System.out.println(urL);
        }
        /*
        * file:/C:/Program%20Files/Java/jre/lib/resources.jar
        * file:/C:/Program%20Files/Java/jre/lib/rt.jar
        * file:/C:/Program%20Files/Java/jre/lib/sunrsasign.jar
        * file:/C:/Program%20Files/Java/jre/lib/jsse.jar
        * file:/C:/Program%20Files/Java/jre/lib/jce.jar
        * file:/C:/Program%20Files/Java/jre/lib/charsets.jar
        * file:/C:/Program%20Files/Java/jre/lib/jfr.jar
        * file:/C:/Program%20Files/Java/jre/classes
        */
    }


    //获取ExtClassLoader的加载路径
    public static void ext_loaderUrls(){
        //获取设置的参数
        String property = System.getProperty("java.ext.dirs");
        String[] urLs = property.split(";");
        for (String urL : urLs) {
            System.out.println(urL);
        }
        /*
         * C:\Program Files\Java\jre\lib\ext
         * C:\WINDOWS\Sun\Java\lib\ext
         */
    }

    //获取类加载器的方式
    public static void getClassLoader() throws Exception{
        //某个类的类加载器
        ClassInit.class.getClassLoader();
        Class.forName("learner.wzn.load.ClassInit").getClassLoader();

        //当前线程类加载器
        Thread.currentThread().getContextClassLoader();

        //获取系统默认类加载器
        ClassLoader.getSystemClassLoader();
    }
}
