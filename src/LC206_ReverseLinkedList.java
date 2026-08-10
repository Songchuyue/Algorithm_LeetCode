public class LC206_ReverseLinkedList {
	static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	private static ListNode reverseList(ListNode head) {
		if(head == null || head.next == null) return head;// length = 0 or 1

		if(head.next.next == null) {// length = 2
			ListNode tem = head.next;
			head.next.next = head;
			head.next = null;
			return tem;
		}

		// length >= 3
		ListNode pre = head, cur = head.next, nex = head.next.next;
		head.next = null;

		while(nex != null) {// ...<-pre cur->head->...
			cur.next = pre;// ...<-pre<-cur head->...
			pre = cur;
			cur = nex;
			nex = nex.next;
		}

		cur.next = pre;

		return cur;
	}

	private static ListNode reverseListPlus(ListNode head) {
		ListNode pre = null, cur = head, nex;
		while(cur != null) {// ... <- pre __ cur -> nex
			nex = cur.next;
			cur.next = pre;
			pre = cur;
			cur = nex;
		}

		return pre;
	}

	public static void main(String[] args) {

	}
}
