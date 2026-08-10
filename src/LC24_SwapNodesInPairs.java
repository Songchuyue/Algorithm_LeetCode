public class LC24_SwapNodesInPairs {
	static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	private static ListNode swapPairs(ListNode head) {
		if(head == null) return null;

		ListNode pre_head = new ListNode(-1);
		pre_head.next = head;

		ListNode pre = pre_head, cur = head, nex;

		while(cur != null) {
			if(cur.next == null) break;
			else nex = cur.next;
			// pre -> cur -> nex -> ...
			// pre -> nex -> cur -> ...
			cur.next = nex.next;
			nex.next = cur;
			pre.next = nex;

			pre = cur;
			cur = cur.next;
		}

		return pre_head.next;
	}

	public static void main(String[] args) {

	}
}
