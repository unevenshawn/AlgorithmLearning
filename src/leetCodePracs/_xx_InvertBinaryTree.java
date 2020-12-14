package leetCodePracs;

import algorithm.tree.binaryTree.BinarySearchTree;

import java.util.Random;
import java.util.Stack;

//进行遍历，将树的所有左右子节点都进行互相交换即可，即temp=左子节，左子节=右子节，右字节=temp;
public class _xx_InvertBinaryTree {
    public static void invertBinaryTree(){
        BinarySearchTree bst=new BinarySearchTree();
        for (int i = 0; i < 10; i++) {
            bst.add(new Random().nextInt());
        }
        System.out.println(bst.toString());
        System.out.println("---------------------");
        bst.printBST(BinarySearchTree.LEVEL);
        System.out.println("---------------------");
        bst.invertBinaryTree();
    }

    public static void main(String[] args) {
        invertBinaryTree();
    }
}
