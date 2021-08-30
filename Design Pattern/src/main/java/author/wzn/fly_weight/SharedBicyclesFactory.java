package author.wzn.fly_weight;

import java.util.HashSet;
import java.util.Set;

//用来创造一批可以共享的Bicycles
class SharedBicyclesFactory {

    private final Set<SharedBicycle> pool = new HashSet<>();

    public SharedBicyclesFactory(int num) {
        for (int i = 0; i < num; i++) {
            pool.add(new SharedBicycle(i + 1));
        }
    }

    public SharedBicycle getBike() {
        SharedBicycle get = null;
        for (SharedBicycle bicycle : pool) {
            if (bicycle.state == 0) {
                get = bicycle;
            }
        }
        return get;
    }

    public int freeCounts(){
        int count = 0;
        for (SharedBicycle bicycle : pool) {
            if (bicycle.state == 0) {
                count++;
            }
        }
        return count;
    }
}
