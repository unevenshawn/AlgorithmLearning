package algorithm.linklist;

import algorithm.linklist.DoubleLinkedList;
import algorithm.linklist.SingleLinkedList;
import algorithm.linklist.SingleLoopLinkedList;

public class Main {
//    public static void main(String[] args) {
//        SingleLinkedList sl=new SingleLinkedList();
//        sl.add(10);
//        sl.remove(0);
//        sl.add(1000);
//        sl.add(20);
//        sl.insert(0,199);
//        sl.add(90);
//        sl.add(30);
//        sl.remove(0);
//        System.out.println(sl.toString());
//        System.out.println(sl.firstIndexOf(90));
//        sl.set(3,300);
//        System.out.println(sl.toString());
//        sl.remove(sl.getSize()-1);
//        System.out.println(sl.toString());
//    }

/*    public static void main(String[] args) {
        DoubleLinkedList dl=new DoubleLinkedList();
        dl.add(10);
        dl.remove(0);
        dl.add(1000);
        dl.add(20);
        dl.add(30);
        dl.insert(0,2);
        dl.insert(3,40);
        dl.add(50);
        System.out.println(dl);
        dl.remove(5);
        dl.remove(dl.getSize()-1);
        dl.set(3,200);
        System.out.println(dl);
    }*/

    //单向循环链表
//    public static void main(String[] args) {
//        SingleLoopLinkedList sll=new SingleLoopLinkedList();
//        sll.add(10);
//        sll.remove(0);
//        sll.add(20);
//        sll.add(30);
//        sll.add(40);
//        System.out.println(sll);
//        sll.insert(0,14);
//        System.out.println(sll);
//        sll.insert(3,100);
//        System.out.println("index-30 "+ sll.firstIndexOf(30));
//        Object o = sll.nextEle(4);
//        System.out.println(o);
//        System.out.println(sll);
//        sll.remove(0);
//        System.out.println(sll.remove(3));
//        System.out.println(sll.nextEle(2));
//        System.out.println(sll);
//
//    }

    public static void main(String[] args) {
        DoubleLinkedList dll=new DoubleLinkedList();
        System.out.println(dll==null);
    }
}
