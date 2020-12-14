package algorithm.linklist;

public class ListTest {
    static void testList(MyList<Integer> list){
        list.add(9);
        list.insert(0,1);
        list.add(99);
        list.add(999);
        list.add(9999);
        System.out.println(list.toString());
        list.insert(4,666);
        list.remove(3);
        System.out.println("99的index"+ list.firstIndexOf(99));

    }
}
