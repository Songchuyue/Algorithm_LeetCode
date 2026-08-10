public class LC160_IntersectingLinkedLists {
	static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	private static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		int len_a = 0, len_b = 0;

		ListNode tem = headA;
		while(tem != null) {
			len_a++;
			tem = tem.next;
		}

		tem = headB;
		while(tem != null) {
			len_b++;
			tem = tem.next;
		}

		while(headA != null && len_a > len_b) {
			len_a--;
			headA = headA.next;
		}

		while(headB != null && len_b > len_a) {
			len_b--;
			headB = headB.next;
		}

		while(headA != null && headB != null) {
			if(headA == headB) return headA;
			headA = headA.next;
			headB = headB.next;
		}

		return null;
	}

	public static void main(String[] args) {

	}
}