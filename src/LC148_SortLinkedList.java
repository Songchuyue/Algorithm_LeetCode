public class LC148_SortLinkedList {
	static class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}

	private ListNode MergeList(ListNode lef, ListNode rig) {
		if(lef == null || rig == null) return lef != null ? lef : rig;

		ListNode head = new ListNode(-1, null);
		ListNode cur = head;

		// cur -> lef or        lef
		//        rig    cur -> rig
		while(lef != null && rig != null) {
			if(lef.val <= rig.val) {
				cur.next =  lef;
				lef = lef.next;
			} else {
				cur.next = rig;
				rig = rig.next;
			}
			cur = cur.next;
		}

		cur.next = lef != null ? lef : rig;

		return head.next;
	}

	public ListNode sortList_Recursion(ListNode head) {
		if(head == null || head.next == null) return head;

		int len = 0;
		ListNode tem = head;
		for(; tem != null; tem = tem.next) len++;

		ListNode rig_head = head;
		for(int i = 1; i < len / 2; i++) rig_head = rig_head.next;
		// ↓         ↓
		// 1 -> 2 -> 3 -> 4 -> 5 -> 6
		// ↓         ↓
		// 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7

		tem = rig_head.next;
		rig_head.next = null;
		rig_head = tem;
		// ↓              ↓
		// 1 -> 2 -> 3    4 -> 5 -> 6
		// ↓              ↓
		// 1 -> 2 -> 3    4 -> 5 -> 6 -> 7

		head = sortList_Recursion(head);
		rig_head = sortList_Recursion(rig_head);
		head = MergeList(head, rig_head);

		return head;
	}

	private ListNode MergeList(ListNode lef, ListNode rig, ListNode pre) {
		if(lef == null || rig == null) {
			pre.next = lef != null ? lef : rig;
			return pre;
		}

		// pre -> lef or        lef
		//        rig    pre -> rig
		while(lef != null && rig != null) {
			if(lef.val <= rig.val) {
				pre.next =  lef;
				lef = lef.next;
			} else {
				pre.next = rig;
				rig = rig.next;
			}
			pre = pre.next;
		}

		pre.next = lef != null ? lef : rig;

		while(pre.next != null) pre = pre.next;

		return pre;
	}

	private ListNode cutList(ListNode head, int size) {
		if(head == null) return head;

		// size = 4
		// 1 -> 2 -> 3 -> 4 -> 5
		// 1 -> 2

		ListNode tem = head; int cnt = 1;
		while(tem.next != null && cnt < size) {
			tem = tem.next;
			cnt++;
		}

		ListNode nex = tem.next;
		tem.next = null;

		return nex;
	}

	public ListNode sortList_Iteration(ListNode head) {
		if(head == null || head.next == null) return head;

		int len = 0;
		ListNode tem = head;
		for(; tem != null; tem = tem.next) len++;

		ListNode pre_head = new ListNode(-1);
		pre_head.next = head;

		// pre_head -> 5 -> 2 -> 4 -> 1 -> 3
		for(int i = 1; i < len; i *= 2) {
			ListNode pre = pre_head, cur = pre_head.next;

			while(cur != null) {
				ListNode lef = cur;
				ListNode rig = cutList(cur, i);
				cur = cutList(rig, i);

				pre = MergeList(lef, rig, pre);
			}
		}

		return pre_head.next;
	}
}
