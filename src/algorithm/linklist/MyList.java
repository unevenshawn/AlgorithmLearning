package algorithm.linklist;

public interface MyList<E> {
    void add(E element);

    int firstIndexOf(E element);

    E remove(int Index);

    void insert(int Index,E element);

    String toString();
}
