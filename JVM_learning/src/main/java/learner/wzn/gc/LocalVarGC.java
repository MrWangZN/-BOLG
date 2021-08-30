package learner.wzn.gc;

//-XX:+PrintGCDetails

//重点看localVarGC3()和localVarGC4()，在localVarGC3()中不会立刻回收需要根据字节码指令

public class LocalVarGC {
    public void localVarGC1(){
        byte[] buffer = new byte[10 * 1024 * 1024];
        System.gc();
    }
    public void localVarGC2(){
        byte[] buffer = new byte[10 * 1024 * 1024];
        buffer = null;
        System.gc();
    }

    //没有gc 移到了老年代
    public void localVarGC3(){
        {
            byte[] buffer = new byte[10 * 1024 * 1024];
        }
        System.gc();
    }
    public void localVarGC4(){
        {
            byte[] buffer = new byte[10 * 1024 * 1024];
        }
        int i = 1;
        System.gc();
    }

    public static void main(String[] args) {
        LocalVarGC gc = new LocalVarGC();
//        gc.localVarGC1();
//        gc.localVarGC2();
        gc.localVarGC3();
//        gc.localVarGC4();
    }
}
