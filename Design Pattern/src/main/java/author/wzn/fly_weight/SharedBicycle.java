package author.wzn.fly_weight;

class SharedBicycle extends Bicycle {
    private int id;

    public SharedBicycle(int num) {
        this.id = num;
    }

    @Override
    void take(String userName) {
        this.state = 1;
        this.userName = userName;
        System.out.println(userName+"使用"+id+"号自行车");
    }

    @Override
    void back() {
        this.state = 0;
        this.userName = null;
    }
}
