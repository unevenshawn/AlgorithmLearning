package algorithm.linklist;


import java.util.StringJoiner;

//单向循环链表的不同之处在于，其首尾相连，最重要就是添加和删除
//链表的泛型和节点的泛型是同一种类型
public class SingleLoopLinkedList<E> implements MyList<E>{


    private static final int ELEMENT_NOT_FOUND = -1;

    private void rangeCheck(int index) {
        //size-1即是表示最后一个节点，如果是add方法，传入index为size，则表示在最后添加一个节点,所以应该能够index取到size
        //size==0表示链表为空
        if (index >= this.size || index < 0 || this.size == 0) {
            throw new IndexOutOfBoundsException(index);
        }

    }

    private void rangeCheckForAdd(int index) {
        if (index > this.size || index < 0) {
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
        //由于first指向了第一个节点，进行index次的迭代，就能获得index位置上的节点
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    public void insert(int index, E element) {
        rangeCheck(index);
        //但因为传入的时候，index可能等于0，而0是合理的
        if (index == 0) {
            Node<E> newNode = new Node<E>(element, this.first);
            this.first = newNode;
            //因为上面一行刚刚创建新节点，所以这儿必须要size++，这样更加严谨
            size++;
            //假如size++不放在这儿
            //由于这儿已经加上1了，size应该加1，但是因为未来得及更新，所以这儿再调用node会出问题
            //又因为这儿如果用node(size)查找node，会因为rangeCheck导致角标越界异常。
            node(this.size-1).next=newNode;
//            System.out.println(node(this.size - 1).element);
//            System.out.println(node(this.size - 1).next.element);

        } else {
            Node<E> prev = node(index - 1);
            prev.next = new Node<>(element, node(index));
            size++;
        }

    }


    //必须考虑首尾
    public E remove(int index) {
        //确保链表不为空
        Node<E> node = first;
        rangeCheck(index);
        if (index == 0) {
            if (size == 1) {
                first = null;
            } else {
                Node<E> last = node(this.size - 1);
                first = first.next;
                last.next = first;
            }
        } else {
            node = node(index);
            Node<E> prev = node(index - 1);
            prev.next = node.next;
        }
        size--;
        return node.element;
    }

    //可以通过finalize方法去验证后续的结点
    public void clear() {
        this.size = 0;
        this.first = null;
    }

    //尾部添加节点
    public void add(E element) {
        if (first == null) {
            first = new Node<>(element, null);
            //如果要表示谁指向谁，一般是对象.指针在等号左边，被指向对象在等号右边
            first.next = first;
        } else {
            node(size - 1).next = new Node<>(element, first);
        }
/*        //以上代码似乎可以做下优化，但测试过了以后不行，因为first的情况
        Node<E> node=size==0?first:node(size-1);
        node.next=new Node<>(element,first);*/

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

        return "size=" + getSize() + "," + sj.toString();
    }




    public E nextEle(int index) {
        return node(index).next.element;
    }

}
