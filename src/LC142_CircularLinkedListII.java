public class LC142_CircularLinkedListII {
	static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	private static ListNode detectCycle(ListNode head) {
		if(head == null || head.next == null) return null;
		if(head.next == head) return head;

		ListNode slow = head, fast = head.next;

		while(fast != null) {
			fast = fast.next;
			if(fast == null || fast.next == null) return null;
			else fast = fast.next;

			slow = slow.next;

			if(slow == fast) {// find cycle
				ListNode tem = head;
				fast = fast.next;

				while(tem != fast) {
					tem = tem.next;
					fast = fast.next;
				}

				return tem;
			}
		}

		return null;
	}

	public static void main(String[] args) {

	}
}
