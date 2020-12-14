package algorithm.linklist;


import java.util.StringJoiner;

//链表的泛型和节点的泛型是同一种类型
public class SingleLinkedList<E> implements MyList<E>{


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
        Node<E> node = first;
        //由于first指向了第一个节点，进行index次的迭代，就能获得index位置上的元素
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    public void insert(int index, E element) {
        //不允许链表为空或在size位上插入
        rangeCheck(index);

        //但因为传入的时候，index可能等于0，而0是合理的
        if (index == 0) {
            //如果index是0.那么让链表的field-first指向新插入的结点，而新结点只需要指向原first上的结点即可
            //因为不存在调用.next，所以不用考虑nullpointer
            first = new Node<E>(element, first);

        } else {
            //先获取要插入位置的上一个节点
            Node<E> prev = node(index - 1);
/*
        //当前index所在节点即是要插入节点所即将指向的下一个节点
        Node<E> insert= new Node<>(element, node(index));
        //上一个节点指向新插入的结点
        prev.next=insert;
*/
            //上述两行代码可以合并
            prev.next = new Node<>(element, node(index));
        }
        size++;
    }


    //必须考虑首尾
    public E remove(int index) {
        //确保链表不为空
        rangeCheck(index);
        Node<E> node = first;
        if (index == 0) {
            //如果是删除首尾的结点，即是field-first指向原first的下一个元素
            first = first.next;
        }else if(index==this.size-1) {
            node(index-1).next=null;
        }else {
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
