package algorithm.tree.binaryTree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings("unchecked")
public class BinarySearchTree<E> {
    //abbr：bst
    //任意一个节点的值都大于左子树所有节点的值，小于所有右子树的所有节点值
    //左右子树也都是二叉搜索树
    //必须存储可以比较的值
    //不能为空
    //无索引
    private int size;
    private Node<E> root;
    private Comparator<E> comparator;
    public static final String PRE = "pre";
    public static final String MID = "mid";
    public static final String POST = "post";
    public static final String LEVEL = "level";


    public BinarySearchTree(Comparator comparator) {
        this.comparator = comparator;
    }

    public BinarySearchTree() {
        this(null);
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void clear() {
        //根节点为空，树即为空
        this.root = null;
    }

    public void add(E element) {
        elementNotNull(element);
        //如果树为空，创建根节点
        if (root == null) {
            root = new Node<>(element, null);
            size++;
        }

        //能到这儿，添加的不是第一个节点
        //先找到父节点
        Node<E> node = root;
        Node<E> parent = root;
        int result = 0;
        //查找到的节点，没有子节点时候，就可以插入了
        while (node != null) {
            result = compare(element, node.element);
            parent = node;
            if (result > 0) {
                node = node.rightChild;
            } else if (result < 0) {
                node = node.leftChild;
            } else {
                //下面一行代码是覆盖原有内容。  覆盖后   还是直接返回，我之前怎么就没想到呢？
                node.element = element;
                return;
            }
        }
        //通过以上的循环找到父节点
        //再通过result与零的大小，确定是要插入父节点的左边还是右边
        //result不可能等于0，等于零就已经返回了
        if (result > 0) {
            parent.rightChild = new Node<>(element, parent);
        } else {
            parent.leftChild = new Node<>(element, parent);
        }
        size++;

    }


    /**
     * @param e1
     * @param e2
     * @return 0表示相等，大于1表示e1大于e2，小于0表示e1小于e2
     * <p>
     * 实现比较的方法：
     * 一、BST <E extends Comparable>，接口中定义比较
     * 二、使用Comparator比较器的接口
     */
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return this.comparator.compare(e1, e2);
        } else {
            //不能在泛型的地方写死，而是在无法通过比较器比较的时候，这儿强制转型
            return ((Comparable<E>) e1).compareTo(e2);
        }
    }


    private void elementNotNull(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Not null element!");
        }
    }

    public void remove(E element) {

    }

    public boolean contains(E element) {
        final boolean[] isContained = {false};
        preTraverse(new Visitor<E>() {
            @Override
            public boolean visit(E e) {
                if (e.equals(element)) {
                    //可以得出是否相等
                    isContained[0] = true;
                    //停止遍历
                    return true;
                }
                return false;
            }
        });
        return isContained[0];
    }

    private static class Node<E> {
        E element;
        Node<E> leftChild;
        Node<E> rightChild;
        //红黑树的时候，父节点很有用
        Node<E> parent;

        //静态内部类，如果构造器也是私有，那么它所在的类也无法创造这个内部类
        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
    }


    //增强遍历的两个方式：1.加入visitor接口， 2.visitor.stop保证一找到元素，马上停止遍历
    public void preTraverse(Visitor<E> visitor) {
        if (visitor == null) throw new IllegalArgumentException("visitor is null!");
        preTraverse(this.root, visitor);
    }

    //以visitor为参数可以自定义访问方式
    private void preTraverse(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;
        visitor.visit(node.element);
        preTraverse(node.leftChild, visitor);
        preTraverse(node.rightChild, visitor);

    }


    public void midTraverse(Visitor<E> visitor) {
        if (visitor == null) throw new IllegalArgumentException("visitor is null!");
        midTraverse(this.root, visitor);
    }

    private void midTraverse(Node<E> node, Visitor<E> visitor) {
        //visitor.stop保证一找到符合要求的元素，就立即返回，不进入左节点的递归中去
        if (node == null || visitor.stop) return;
        midTraverse(node.leftChild, visitor);
        //保证找到对应节点后，不再本轮继续往后递归执行
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        midTraverse(node.rightChild, visitor);
    }


    public void postTraverse(Visitor<E> visitor) {
        if (visitor == null) throw new IllegalArgumentException("visitor is null!");
        postTraverse(this.root, visitor);
    }

    private void postTraverse(Node<E> node, Visitor<E> visitor) {
        //visitor.stop=>return可以保证找到目标结果后立即完成返回
        if (node == null || visitor.stop) return;
        postTraverse(node.leftChild, visitor);
        postTraverse(node.rightChild, visitor);

        //必须要有这一句，否则的话，找到目标元素后，还会再进入下一轮，再访问一次元素
        if (visitor.stop) return;

        //记录访问结果
        visitor.stop = visitor.visit(node.element);
    }


    public void levelTraverse(Visitor<E> visitor) {
        if (visitor == null) throw new IllegalArgumentException("visitor is null!");
        levelTraverse(this.root, visitor);
    }


    private void levelTraverse(Node<E> node, Visitor<E> visitor) {
        if (root == null) return;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> poll = queue.poll();
            visitor.stop = visitor.visit(poll.element);
            if (visitor.stop) return;
            if (poll.leftChild != null) {
                queue.offer(poll.leftChild);
            }
            if (poll.rightChild != null) {
                queue.offer(poll.rightChild);
            }
        }
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(sb, root, "");
        return sb.toString();
    }

    private void toString(StringBuilder sb, Node<E> node, String prefix) {
        if (node == null) return;

        sb.append(prefix + node.element + "\n");
        toString(sb, node.leftChild, prefix + " --L-- ");
        toString(sb, node.rightChild, prefix + " --R-- ");
    }

    //递归计算二叉树高度
    private int height(Node<E> node) {
        if (node == null) return 0;

        return Math.max(height(node.leftChild), height(node.rightChild)) + 1;
    }


    public int treeHeight() {
        return height(root);
    }

    public int getHeight() {
        Node<E> node = root;
        if (node == null) return 0;
        int rowSize = 1;
        int height = 0;
        Queue<Node<E>> queue = new LinkedList();
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node<E> poll = queue.poll();
            //
            rowSize--;

            if (poll.leftChild != null) {
                queue.offer(poll.leftChild);
            }
            if (poll.rightChild != null) {
                queue.offer(poll.rightChild);
            }
            //rowSize==0即意味着树的这一层已经访问完毕
            if (rowSize == 0) {
                //在一轮循坏中，当该层访问完毕后，要保证所有剩下的元素已经添加进入队列，而此时的队列中剩余元素的个数即是队列的size
                rowSize = queue.size();
                height++;
            }
        }
        return height;
    }


    //是否完全二叉树，先留着
    public boolean isComplete() {
        return false;
    }


    public void printBST(String mode) {
        if (PRE.equalsIgnoreCase(mode)) {
            prePrintTraverse(root);
        } else if (MID.equalsIgnoreCase(mode)) {
            midPrintTraverse(root);
        } else if (POST.equalsIgnoreCase(mode)) {
            postPrintTraverse(root);
        } else if (LEVEL.equalsIgnoreCase(mode)) {
            levelPrintTraverse(root);
        } else {
            throw new IllegalArgumentException("No Such Ways To Traverse!");
        }
    }


    private void levelPrintTraverse(Node<E> node) {
        if (root == null) return;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> poll = queue.poll();
            System.out.println(poll.element);
            if (poll.leftChild != null) {
                queue.offer(poll.leftChild);
            }
            if (poll.rightChild != null) {
                queue.offer(poll.rightChild);
            }
        }
    }

    //先序遍历，中序遍历，后续遍历的前中后都是相对于根节点而言的
    //先序遍历优先访问根节点
    private void prePrintTraverse(Node<E> node) {

        if (node != null) {
            System.out.println(node.element);
            prePrintTraverse(node.leftChild);
            prePrintTraverse(node.rightChild);
        } else {
            return;
        }
    }

    //后续遍历最后访问根节点
    private void postPrintTraverse(Node<E> node) {
        if (node != null) {
            postPrintTraverse(node.leftChild);
            postPrintTraverse(node.rightChild);
            System.out.println(node.element);
        } else {
            return;
        }
    }

    //中序遍历中间访问根节点
    private void midPrintTraverse(Node<E> node) {

        if (node != null) {
            midPrintTraverse(node.leftChild);
            System.out.println(node.element);
            midPrintTraverse(node.rightChild);
        } else {
            return;
        }
    }


    public void invertBinaryTree() {
        if (root == null) return;
        Queue<Node<E>> que = new LinkedList<>();
        que.offer(root);

        while (!que.isEmpty()) {
            Node<E> temp;
            Node<E> pop = que.poll();
            temp = pop.leftChild;
            pop.leftChild = pop.rightChild;
            pop.rightChild = temp;
            System.out.println(pop.element);
            if (pop.leftChild != null) {
                que.offer(pop.leftChild);
            }
            if (pop.rightChild != null) {
                que.offer(pop.rightChild);
            }
        }
    }

    /**
     * 1.前驱节点：按照中序遍历时的前一个节点。此时对于BST来说，遍历顺序即是升序排列顺序
     * 2.对于所有树，如果左子树不为空，那么前驱节点肯定该节点的左节点的右子节点，右子节点，右子节点，...
     * 直到右子节点为空
     * 3.如果左子树为空，那么前驱节点，肯定是父节点的父节点，父节点，父节点...
     * 直到该节点是父节点的右子树
     * 4.如果节点的左子节点为空，并且父节点为空，
     * 那么此时节点为根节点，并且无前驱节点
     *
     * @param node
     * @return
     */
    public Node<E> predecessor(Node<E> node) {
        if (node == null) return null;
        if (node.leftChild != null) {
            node = node.leftChild;
            while (node.rightChild != null) {
                node = node.rightChild;
            }
            return node;
        } else {
            while (node.parent!=null&&node!= node.parent.rightChild) {
                node = node.parent;
            }
            //上述循环退出时，要么是Node.parent为空，那么就说明是根节点，没有前驱节点
            //否则的话，应该就是Node.parent是它的前驱节点
            return node.parent;
        }
    }


    /**
     * 后继节点：
     * 1.如果node.right!=null, 那么node.right.left.left....,直到left为空
     * 2.如果node.right==null（比如是左子树的右节点）, 那么是node.parent.parent.parent。。。。直到是parent的左子节点
     * 3. 如果node.right==null 并且node.parent==null，那么则是根节点
     *
     * @param node
     * @return
     */
    private Node<E> successor(Node<E> node){

        return  null;
    }


}
