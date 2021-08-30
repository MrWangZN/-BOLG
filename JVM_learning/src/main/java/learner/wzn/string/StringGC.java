package learner.wzn.string;


/**
* @Author: 王振南
* @Date: 2021/8/26
* @Description: 字符串常量池会发生GC
 *          -Xmx15m -Xmx15m -xx:+PrintStringTableStatics -XX:+PrintGCDetails
*/
public class StringGC {
    public static void main(String[] args) {
        int j = 100000;
        for (int i = 0; i < j; i++) {
            String.valueOf(i).intern();
        }
//        StringTable statistics:
//        Number of buckets       :     60013 =    480104 bytes, avg   8.000
//        Number of entries       :     58214 =   1397136 bytes, avg  24.000
//        Number of literals      :     58214 =   3318856 bytes, avg  57.011
//        可以看出常量池并没有达到100000数字，发生了gc
    }
}
