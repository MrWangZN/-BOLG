package author.wzn.Observer;

/*
*
* 观察者模式  - 也称为发布订阅模式
*   定义：对象间的以一种一堆多依赖关系,使得当一个对象状态发生改变时,其相关依赖者皆得到通知并更新
*
*   观察者模式使用例子 : spring异常处理advice，当我们发生了异常状态发生了改变，通知观察者，观察者发现这样的事情发生对异常进行处理。
*
* 
* */


import java.util.ArrayList;
import java.util.List;

public class ObserverPattern {
    public static void main(String[] args) {
        SpecificSubject specificSubject = new SpecificSubject();

        specificSubject.addObserver(() -> System.out.println("观察者一: 我知道了"));
        specificSubject.addObserver(() -> System.out.println("观察者二: 我知道了"));
        specificSubject.addObserver(() -> System.out.println("观察者三: 我知道了"));

        specificSubject.setChanged(true);

        specificSubject.notifyObserver();

    }
}

interface Subject { //主题对象 负责添加和通过观察者

    void addObserver(Observer observer);
    void notifyObserver();

}
class SpecificSubject implements  Subject{ //主题的主题对象

    private boolean changed = false;
    private List<Observer> list;

    public SpecificSubject() {
        list = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        list.add(observer);
    }

    @Override
    public void notifyObserver() {
        if (!changed) {
            return;
        }
        clearChanged();

        list.forEach(Observer::update);
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public synchronized boolean hasChanged() {
        return changed;
    }

    protected synchronized void clearChanged() {
        changed = false;
    }
}

@FunctionalInterface
interface Observer{  //观察者

    void update();

}

