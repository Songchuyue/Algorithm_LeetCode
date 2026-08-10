public class LC141_CircularLinkedList {
	static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	private boolean hasCycle(ListNode head) {
		if(head == null || head.next == null) return false;
		if(head.next == head) return true;

		ListNode slow = head, fast = head.next;

		while(fast != null) {
			fast = fast.next;
			if(fast == null || fast.next == null) return false;
			else fast = fast.next;

			slow = slow.next;

			if(slow == fast) return true;
 		}

		return false;
	}

	public static void main(String[] args) {

	}
}
