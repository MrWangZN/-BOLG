package author.wzn.adapter;

import java.util.ArrayList;
import java.util.List;


/*
* 设计模式 - 适配器模式
* 将一个类的接口转换成客户希望的另外一个接口，使得原本由于接口不兼容而不能一起工作的那些类能一起工作。
*
* 如: 本例子中,一个人想要看外国电影,但是不懂外国的外语,那么就要将这个电影处理翻译一下,可以是翻译字幕，可以是配音这不是我们的重点
*     我们的重点是要让Viewer能够看得懂。
*
*
* 例子:  spring MVC中，不同的handle有不同的适配器，如果请求是实现Controller接口、使用Controller注解等就使用相应的注解
*       为的是保证其功能不变但是能够正常运行。原本的是不能在一起工作的。
*
*/
public class AdapterPatter {

    private static final List<Adapter> adapters = new ArrayList<>();

    static {
        adapters.add(new ToChineseAdapter());
        adapters.add(new ToEnglishAdapter());
    }

    public static void main(String[] args) {
//        Viewer chinese = new Viewer("English", new Movie());
        Viewer chinese = new Viewer("Chinese", new Movie());
        watching(chinese);
    }

    public static void watching(Viewer viewer) {
        Adapter adapter = getAdapter(viewer);
        adapter.handle(viewer.getWatchingMovie());
    }

    public static Adapter getAdapter(Viewer viewer) {
        for (Adapter next : adapters) {
            if (next.support(viewer.getMother_tongue())) {
                return next;
            }
        }
        return null;
    }

}
