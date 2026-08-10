public class LC234_PalindromicLinkedList {
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

	private static boolean isPalindrome(ListNode head) {
		int len = 0;

		ListNode tem = head;
		while(tem != null) {
			len++;
			tem = tem.next;
		}

		int half = (len + 1) / 2;
		tem = head;
		while(half-- > 0) {
			tem = tem.next;
		}

		ListNode revHead = reverseList(tem);

		half = len / 2;
		while(half-- > 0) {
			if(head.val != revHead.val) return false;
			head = head.next;
			revHead = revHead.next;
		}

		return true;
	}

	public static void main(String[] args) {

	}
}
