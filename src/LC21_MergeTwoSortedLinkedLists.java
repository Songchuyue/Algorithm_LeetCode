public class LC21_MergeTwoSortedLinkedLists {
	static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	private static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
		if(list1 == null) return list2;
		if(list2 == null) return list1;

		ListNode head = list1;
		if(list1.val > list2.val) {
			list1 = list2;
			list2 = head;
			head = list1;
		}

		while(list2 != null) {
			if(list1.next == null) {
				list1.next = list2;
				return head;
			}

			ListNode nex1 = list1.next;
			ListNode nex2 = list2.next;

			// list1 -> nex1  -> ...
			//          list2 -> nex2

			while(nex1 != null && nex1.val <= list2.val) {// list1.next == null || nex.val > list2.val
				list1 = nex1;
				nex1 = nex1.next;
			}

			if(nex1 == null) continue;

			list1.next = list2;
			list2.next = nex1;
			list1 = list2;
			list2 = nex2;
		}

		return head;
	}

	private static ListNode mergeTwoLists_plus(ListNode list1, ListNode list2) {
		ListNode head = new ListNode(0);
		ListNode cur = head;

		while(list1 != null && list2 != null) {
			if (list1.val <= list2.val) {
				cur.next = list1;
				list1 = list1.next;
			} else {
				cur.next = list2;
				list2 = list2.next;
			}
			cur = cur.next;
		}

		cur.next = list1 != null ? list1 : list2;

		return head.next;
	}

	public static void main(String[] args) {

	}
}
