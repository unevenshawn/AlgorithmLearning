package leetCodePracs;

import leetCodePracs.ListNode;

public class _206_linklistReverse {

    // 递归
    public static ListNode reverseList(ListNode node) {
        ListNode newNode=null;
        while (node!=null){
            ListNode temp=node.next;
            node.next=newNode;
            newNode=node;
            node=temp;
        }
        return newNode;
    }

    public static void main(String[] args) {
        ListNode ln=new ListNode(2);
        ln.next=new ListNode(3);
        ln.next.next=new ListNode(4);
        ListNode newln=reverseList(ln);
        System.out.println(newln.val);
        System.out.println(newln.next.val);
        System.out.println(newln.next.next.val);
    }


    // 非递归，即迭代

}