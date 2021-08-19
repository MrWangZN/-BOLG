package author.wzn.algorithms.practice.leetCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//深度优先、广度优先都可以想想
public class LeetCode_690 {

    public static void main(String[] args) {

    }
    //使用了递归  方法一的核心思想都是深度优先，差别就在如何快速的查找到员工，下次再有这样情况的时候，想想map
    //不要总是去遍历全部的员工
    public static int getImportance(List<Employee> employees, int id) {
        Employee employee = null;
        for(Employee item : employees){
            if(item.id == id){
                employee = item;
                break;
            }
        }
        return getImp(employee, employees);
    }
    public static int getImp(Employee item,List<Employee> employees){
        List<Integer> list =  item.subordinates;
        int n = item.importance;
        for(Employee item2 : employees){
            if(list.contains(item2.id)){
                if(item2.subordinates.size()!=0){
                    n+= getImp(item2,employees);
                }else{
                    n += item.importance;
                }
            }
        }
        return n;
    }

    //两者思路大致思路是一样的,差别在于能否快速找到Employee , map提高了查找的效率
    public static int getImportance_2(List<Employee> employees, int id) {
        HashMap<Integer, Employee> map = new HashMap<>();
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        return dfs(id,map);
    }
    private static int dfs(int id,Map<Integer, Employee> map) {
        Employee employee = map.get(id);
        int n = employee.importance;
        List<Integer> subordinates = employee.subordinates;
        for (Integer subordinate : subordinates) {
            Employee sub = map.get(subordinate);
//            if (sub.subordinates.size() > 0) {
//                n += dfs(sub.id, map);
//            }else{
//                n += sub.importance;
//            }
//            直接加是可以的
            n+= dfs(sub.id, map);
        }
        return n;
    }

    static  class Employee{
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }
}
