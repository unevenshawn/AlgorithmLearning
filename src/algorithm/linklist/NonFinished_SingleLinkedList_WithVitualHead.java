package algorithm.linklist;


import java.util.StringJoiner;

/*
构造一种带有虚拟头结点的链表
1.增加一个构造器，创造虚拟头结点
2.node方法中的迭代需要从first.next开始
3. insert方法中，Node<E> prev = size==0?first:node(index - 1);
4.以下链表还有问题，只修改了以上三处，并不完整
 */
//链表的泛型和节点的泛型是同一种类型
public class NonFinished_SingleLinkedList_WithVitualHead<E> {

    public NonFinished_SingleLinkedList_WithVitualHead(){
        first=new Node<>(null,null);
    }

    private static final int ELEMENT_NOT_FOUND = -1;

    private void rangeCheck(int index) {
        //size-1即是表示最后一个元素，如果是add方法，传入index为size，则表示在最后添加一个元素,所以应该能够index取到size
        //size==0表示链表为空
        if (index >= this.size || index < 0||this.size==0) {
            throw new IndexOutOfBoundsException(index);
        }

    }

    private void rangeCheckForAdd(int index){
        if (index >this.size || index < 0) {
            throw new IndexOutOfBoundsException(index);
        }
    }

    public int getSize() {
        return this.size;
    }

    //表示链表的长度
    private int size;
    //存储链表的头结点
    private Node<E> first;



    public E get(int index) {
        return node(index).element;
    }

    public E set(int index, E element) {
        Node<E> node = node(index);
        E oldEle = node.element;
        node.element = element;
        return oldEle;
    }



    private Node<E> node(int index) {
        rangeCheck(index);
        //虚拟头结点，需要修改这儿
        Node<E> node = first.next;
        //由于first指向了第一个节点，进行index次的迭代，就能获得index位置上的元素
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    public void insert(int index, E element) {
            //对此处代码做了修改
            Node<E> prev = size==0?first:node(index - 1);

            prev.next = new Node<>(element, node(index));

        size++;
    }

    public E remove(int index) {
        //确保链表不为空
        rangeCheck(index);
        Node<E> node = first;
        if (index == 0) {
            //如果是删除首尾的结点，即是field-first指向原first的下一个元素
            first = first.next;
        } else {
            node = node(index);
            //remove即为index的结点，其上一个节点指向index下一个节点
            node(index - 1).next = node(index + 1);
        }
        size--;
        return node.element;
    }

    //可以通过finalize方法去验证后续的结点
    public void clear() {
        this.size = 0;
        this.first = null;
    }

    public void add(E element) {
        if (first == null) {
            first = new Node<>(element, null);
        } else {
            node(size - 1).next = new Node<>(element, null);
        }
        size++;
    }

    private static class Node<E> {
        E element;
        //单链表只要一个指针
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    public int firstIndexOf(E element) {
        Node<E> node = first;
        //如果element==null的话，不能调用element.equals(node.element)
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (null == node.element) return i;

                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) return i;

                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    public String toString() {
        StringJoiner sj = new StringJoiner(",", "[", "]");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            sj.add(node.element.toString());
            node = node.next;
        }
        //另外一种遍历方法，
//        while (node!=null){
//            node=node.next;
//        }

        return "size="+getSize()+","+sj.toString();
    }

}
