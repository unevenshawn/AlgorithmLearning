package algorithm.queue;

import java.util.NoSuchElementException;


/**
 *  未完成
 */
public class NotFinishedCircleDeque<E> {
    //存储队列头部所在指针
    private int front;
    private int size;
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;


    public int getSize() {
        return size;
    }

    public NotFinishedCircleDeque() {
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

    private int rear(){
        return (front+this.size-1)%elements.length;
    }

    //deque要在这儿改
    public void offerFront(E e) {
        ensureCapacity(size+1);
        int place = (front + size) % elements.length;
        elements[place] = e;
        size++;
    }

    public void offerRear(E e) {
        //通过队列的size+1和数组的容量比较，保证数组容量总是充足，这样就可以使得下面的通过取模获得索引总是不会出错。
        ensureCapacity(size+1);
        //本来元素要插入到size的index处，但由于可能要插入到数组的头部，所以要取模
        //front+size可以标定队尾空缺的元素，取模可以保证能够插入到队首空缺的位置
        int place = (front + size) % elements.length;
        elements[place] = e;
        size++;
    }

    //deque要在这儿改
    public E pollRear() {
        if (size==0) throw new NoSuchElementException("Queue is empty!");
        E queFront = elements[front];
        //clear the original place
        elements[front]=null;
        //mod ensures front pointer of queue points to the front of the array in case of polling from the end of the array
        front=(front+1)%elements.length;
        size--;
        return queFront;
    }

    public E pollFront() {
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
     * 这个同时也是动态数组动态扩容的方法
     * @param capacity 传入的是队列size
     */
    private void ensureCapacity(int capacity){
        int oldcapa=this.elements.length;
        if (oldcapa>=capacity) return;
        //位运算右移是除以2，左移是乘以2
        int newcapa=oldcapa+(oldcapa>>1);
        E[] newarry= (E[]) new Object[newcapa];
        for (int i = 0; i < oldcapa; i++) {
            newarry[i]=elements[i];
        }
        this.elements=newarry;
    }
}
