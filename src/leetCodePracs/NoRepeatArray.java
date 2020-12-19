package leetCodePracs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author unevenshawn
 * @create 2020-12-17 22:59
 */
public class NoRepeatArray {
    public static  <T> ArrayList deleteSame(ArrayList<T> arrayList){
        for (int i = 0; i < arrayList.size(); i++) {
            Object o = arrayList.get(i);
            for (int j = 0; j <arrayList.size(); j++) {
                if (i==j){
                    continue;
                }
                System.out.println(arrayList.size());
                Object o1=arrayList.get(j);
                if (o.equals(o1)) arrayList.remove(o1);
            }
            if (arrayList.size()==2&&arrayList.get(0).equals(arrayList.get(1))){
                arrayList.remove(0);
            }
        }
        return arrayList;
    }

    public static void main(String[] args) {
        ArrayList a= new ArrayList();
        a.add("a");
        a.add("b");
        a.add("c");
        a.add("d");
        a.add("e");
        a.add("e");
        a.add("e");
        a.add("b");
        ArrayList arrayList = deleteSame(a);
        System.out.println(arrayList.toString());
    }



}
