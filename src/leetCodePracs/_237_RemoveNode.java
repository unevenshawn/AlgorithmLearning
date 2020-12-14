package leetCodePracs;

public class _237_RemoveNode {

	public void deleteNode(ListNode ln) {
		ln.val = ln.next.val;
		ln.next = ln.next.next;
	}

}
