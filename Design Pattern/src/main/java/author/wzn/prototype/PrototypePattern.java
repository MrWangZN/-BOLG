package author.wzn.prototype;

/*
*
* @Date: 2021/8/20
* @Description: 设计模式 - 原型模式
*               定义 : 用一个已经创建的实例作为原型，通过复制该原型对象来创建一个和原型相同或相似的新对象。
*          在通常: 对象的内部有很多内容无法获取，或者操作十分复杂。便将复制的操作交给要克隆的对象处理。就像细胞克隆一样
*
*          即使我们每次都可以获取到对象的属性值，进行赋值，但是每次都是重复的操作。那么还不如提取成方法，这时候最好的方式就是类似这样的。
*          但是事实的不总是所有的内容都是可以被访问的。因此将克隆的逻辑放在实例对象的类中。
*/
public class PrototypePattern {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
//        Cell cell = new Cell();
//        System.out.println(cell.toString());
//        Cell cloneCell = new Cell(cell);
//        System.out.println(cloneCell.toString());
    }

    //   击败93.16%
    public static String reverseStr(String s, int k) {
        int length = s.length();
        if(length == 1 || k == 1){
            return s;
        }
        int lengthCount = length;
        char[] s1 =  s.toCharArray();
        int left = 0;
        int time = 1;
        int right;
        int push;
        while(lengthCount > 0){
            if(lengthCount < 2*k){
                push = left;
                right = lengthCount >= k ? time * k - 1 : length - 1;
                char temp;
                while(push<right){
                    temp = s1[right];
                    s1[right--] = s1[push];
                    s1[push++] =temp;
                }
                return String.valueOf(s1);
            }
            else{
                push = left;
                right = left + k -1;
                char temp;
                while(push<right){
                    temp = s1[right];
                    s1[right--] = s1[push];
                    s1[push++] =temp;
                }
                left+=2*k;
                lengthCount = length - left;
            }
            time+=2;
        }
        return String.valueOf(s1);
    }
}
class Cell implements Cloneable{

    private String id;

    private String name;

    public Cell(){
        this.id = "id" + Math.random();
        this.name = "name" + Math.random();
    }

    public Cell(Cell cell){
        this.id = cell.id;
        this.name = cell.name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}