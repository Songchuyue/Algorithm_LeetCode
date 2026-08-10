public class LC19_RemoveTheNthNodeFromTheEndOfTheLinkedList {
	static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	private static ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode pre_head = new ListNode(-1);
		pre_head.next = head;

		ListNode cur = pre_head, fas = pre_head;
		for(int i = 1; i <= n; i++) {
			fas = fas.next;
		}

		while(fas.next != null) {
			cur = cur.next;
			fas = fas.next;
		}// cur -> Nth -> ... -> fas

		cur.next = cur.next.next;

		return pre_head.next;
	}

	public static void main(String[] args) {

	}
}
