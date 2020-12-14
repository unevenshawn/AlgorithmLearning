package leetCodePracs;

import java.util.LinkedList;

//判断一个列表是否有环
public class _141_CircleLinkList {

    public static boolean isLinkListCircled(ListNode node){
        if (node==null||node.next==null){
            return  false;
        }
        ListNode fp=node.next;
        ListNode sp=node;
        while(!fp.equals(sp)){
            if (fp.next.next==null||sp.next==null){
                return false;
            }
            fp=fp.next.next;
            sp=sp.next;
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode ln=new ListNode(2);
        ln.next=new ListNode(3);
        ln.next.next=new ListNode(4);
        boolean linkListCircled = isLinkListCircled(ln);
        System.out.println(linkListCircled);
        ln.next.next.next=ln;
        System.out.println(isLinkListCircled(ln));
    }
}
