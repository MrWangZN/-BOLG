package learner.wzn.load;
/*
*
* @Date: 2021/8/22
* @Description: 什么时候要自定义类加载器？
*               1、隔离加载类              如果类冲突（重名）了,可以自定义类加载器隔离
*               2、修改类的加载方式         修改
*               3、扩展加载源              ...
*               4、防止源码泄露（加密、解密） java代码如果被反编译等
*
*          自定义步骤: 继承ClassLoader（抽象类）,实现自己的类加载器
*                    编写findClass()方法
*          ps: 如果没有太过于复杂的需求,可以直接继承URLCLassLoader实现,避免自己去编写findClass()方法以及获取字节流的方式,更加简洁。
*/
public class CustomerClassLoader {
}
