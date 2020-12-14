package algorithm.tree.binaryTree;
//必须放在单独class文件中，放在BST中会报错
public  abstract class Visitor<E> {
    //定义遍历时候访问的方式
    abstract boolean visit(E e);
    //成员变量用于存储是否停止遍历
    boolean stop;
}
