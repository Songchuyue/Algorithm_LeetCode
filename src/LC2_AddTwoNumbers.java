public class LC2_AddTwoNumbers {
	static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if(l1.val == 0 && l1.next == null) return l2;
		if(l2.val == 0 && l2.next == null) return l1;

		int len1 = 0, len2 = 0;

		ListNode head = l1;
		while(head != null) {
			len1++;
			head = head.next;
		}

		head = l2;
		while(head != null) {
			len2++;
			head = head.next;
		}

		head = l1;
		if(len2 > len1) {
			l1 = l2;
			l2 = head;
			head = l1;
		}

		while(l2 != null) {
			int tem = l1.val + l2.val;

			if(tem <= 9) l1.val = tem;
			else {
				l1.val = tem % 10;
				if(l1.next != null) l1.next.val++;
				else l1.next = new ListNode(1);
			}

			l1 = l1.next;
			l2 = l2.next;
		}

		while(l1 != null && l1.val == 10) {
			l1.val = 0;

			if(l1.next != null) l1.next.val++;
			else l1.next = new ListNode(1);

			l1 = l1.next;
		}

		return head;
	}

	private static ListNode addTwoNumbers_plus(ListNode l1, ListNode l2) {
		if(l1.val == 0 && l1.next == null) return l2;
		if(l2.val == 0 && l2.next == null) return l1;

		ListNode head = new ListNode(-1);
		ListNode cur = head;

		while(l1 != null || l2 != null) {
			if(cur.next == null) cur.next = new ListNode(0);
			cur = cur.next;

			cur.val += (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0);

			if(cur.val > 9) {
				cur.val -= 10;
				cur.next = new ListNode(1);
			}

			// cur = cur.next;

			if(l1 != null) l1 = l1.next;
			if(l2 != null) l2 = l2.next;
		}

		return head.next;
	}

	public static void main(String[] args) {

	}
}
