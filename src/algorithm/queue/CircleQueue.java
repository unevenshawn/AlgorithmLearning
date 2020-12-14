package algorithm.queue;

import java.util.*;


/**
 * 最重要是掌握取模获得索引的计算，int index = (front + size) % elements.length;
 */
public class CircleQueue<E> {
    //存储队列头部所在指针
    private int front;
    private int size;
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;


    public int getSize() {
        return size;
    }

    public CircleQueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E front() {
        return elements[front];
    }

    //传入一个索引，获得真实队列中的索引
    private int index(int index){
        return (front+index)%elements.length;
    }


    public void offer(E e) {
        //通过队列的size+1和数组的容量比较，保证数组容量总是充足，这样就可以使得下面的通过取模获得索引总是不会出错。
        ensureCapacity(size+1);
        //本来元素要插入到size的index处，但由于可能要插入到数组的头部，所以要取模
        //front+size可以标定队尾空缺的元素，取模可以保证能够插入到队首空缺的位置
        int place = (front + size) % elements.length;
        elements[place] = e;
        size++;

    }

    public E poll() {
        if (size==0) throw new NoSuchElementException("Queue is empty!");
        E queFront = elements[front];
        //clear the original place
        elements[front]=null;
        //mod ensures front pointer of queue points to the front of the array in case of polling from the end of the array
        front=(front+1)%elements.length;
        size--;
        return queFront;
    }


    /**
     * 扩容的时候，得将front重置为0.否则会导致数据覆盖问题
     * 这个同时也是动态数组动态扩容的方法
     * @param capacity 传入的是队列size
     */
    private void ensureCapacity(int capacity){
        int oldcapa=this.elements.length;
        if (oldcapa>=capacity) return;
        //位运算右移是除以2，左移是乘以2
        int newcapa=oldcapa+(oldcapa>>1);
        E[] newarry= (E[]) new Object[newcapa];
        for (int i = 0; i < size; i++) {
            newarry[i]=elements[index(i)];
        }
        //通过上面的for循环，front的元素已经放在了新数组的第一个
        front=0;
        this.elements=newarry;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder("front="+elements[front]+", [");
        for (int i = 0; i < elements.length; i++) {
            if (i!=0) sb.append(",");
            sb.append(elements[i]);

        }
        sb.append("]");
        return sb.toString();
    }
}
