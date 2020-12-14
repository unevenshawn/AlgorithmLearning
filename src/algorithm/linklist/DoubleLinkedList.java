package algorithm.linklist;


import java.util.StringJoiner;


//与单链表相比，双链表可以从尾部开始向前搜索
//链表的泛型和节点的泛型是同一种类型
public class DoubleLinkedList<E> implements MyList<E>{

    private static final int ELEMENT_NOT_FOUND = -1;

    //表示链表的长度
    private int size;
    //存储链表的头结点
    private Node<E> first;
    //双向链表具有尾结点
    private Node<E> last;

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
        //位运算>>1即是表示整除以2
        //if成立则表示从首端搜索会快些，否则尾端
        if (index < size >> 1) {
            Node<E> node = first;
            //由于first指向了第一个节点，进行index次的迭代，就能获得index位置上的节点
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node<E> node = last;
            //举例说明，比如size=5，查找的index=2，正序迭代，则初始值为0，表示从第一个开始查找，边界值为index，
            // 逆序则从最后一个值开始，最后一个值即为size-1，从后往前，边界值同样为index
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
    }


    //我这儿的insert方法不允许在index==size的位置上插入节点
    public void insert(int index, E element) {
        rangeCheckForAdd(index);
        //但因为传入的时候，index可能等于0，而0是合理的
        if (index == 0) {
            //如果index是0.那么让链表的field-first指向新插入的结点，而新结点只需要指向原first上的结点即可，新节点的上一个节点指向null
            //因为不存在调用.next，所以不用考虑nullpointer
            first = new Node<E>(element, null, first);

        } else {
            //由于是双向链表，获取到index的节点，就很自然获取到前后的节点
            //要做的有三项，index上的node，其上一个节点即preNode指向新节点new Node，新节点的上下分别指向preNode和index上的node
            Node<E> node = node(index);
            //获取上一个节点
            Node<E> preNode = node.prev;
            //等号左边是：index上的结点，其node指向新节点，等号右边是新节点上的前后指针分别指向preNode和node
            node.prev = new Node<>(element, preNode, node);
            //让上一个节点的next指针指向新节点的
            preNode.next = node.prev;
        }
        //确保last指向最后一个节点，其实也没必要，因为我的这个insert方法并不能够让其将
        size++;
    }


    //必须考虑首尾
    public E remove(int index) {
        //确保链表不为空
        rangeCheck(index);
        Node<E> node ;
        if (index == 0) {
            node= first;
            //之后first上的节点，其prev指针指向null

            //如果是删除首尾的结点，即是field-first指向原first的下一个节点
            first = first.next;
            if (first!=null){
            first.prev = null;}else{
                last=first;
            }

        } else if (index == this.size - 1) {//必须考虑index==size-1的情况
            //先保存起来
            node=node(index);
            Node<E> preNode=node.prev;
            last=preNode;
            //以免执行本行后，调用node方法查找不到
            preNode.next=null;
            //不确定是否需要这一步，如果再报错，那么说明这一行是不需要的，也就是说，最后一个节点，只要别的节点不指向它，不引用它即可，哪怕它引用别的，也会被回收
            node.prev=null;
        } else {
            node = node(index);
            Node<E> preNode=node.prev;
            Node<E> nextNode=node.next;
            //remove即为index的结点，其上一个节点指向index下一个节点，其下一个节点指向index的上一个节点
            preNode.next=nextNode;
            nextNode.prev=preNode;
        }
        size--;
        return node.element;
    }


    public void clear() {
        this.size = 0;
        this.first = null;
        this.last = null;
/*      java中GC策略决定上述的代码只要执行，链表中的其它node会被回收，因为其不被GC root对象引用
        java常见GC root对象：
        1.通过System Class Loader或者Boot Class Loader加载的class对象，通过自定义类加载器加载的class不一定是GC Root
        2.处于激活状态的线程
        3.栈中的对象
        4.JNI栈中的对象
        5.JNI中的全局对象
        6.正在被用于同步的各种锁对象
        7.JVM自身持有的对象，比如系统类加载器等。
        */


    }

    public void add(E element) {
        if (first == null) {
            first = new Node<>(element, null, null);
            last = first;
        } else {
            last.next = new Node<>(element, last, null);
            last = last.next;
        }
        size++;
    }


    private static class Node<E> {
        E element;
        //单链表只要一个指针
        Node<E> next;
        Node<E> prev;

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
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

}
