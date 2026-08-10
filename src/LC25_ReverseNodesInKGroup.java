public class LC25_ReverseNodesInKGroup {
	static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	private static ListNode reverseKGroup(ListNode head, int k) {
		if(k == 1) return head;

		ListNode pre_head = new ListNode(-1);
		pre_head.next = head;

		ListNode pre = pre_head, nex, cur = head.next, lef, rig, tem;

		while(cur != null) {
			// pre -> lef -> cur -> rig -> ... -> nex
			nex = pre.next;
			for (int i = 1; i <= k; i++) {
				if(nex == null) return pre_head.next;
				nex = nex.next;
			}

			lef = pre.next;
			cur = lef.next;
			rig = cur.next;
			tem = pre.next;

			// pre -> ... -> rig -> cur -> lef -> nex
			lef.next = nex;

			while(rig != nex) {
				cur.next = lef;
				lef = cur;
				cur = rig;
				rig = rig.next;
			}
			// rig == nex
			cur.next = lef;
			pre.next = cur;
			pre = tem;
			cur = nex;
		}

		return pre_head.next;
	}

	public static void main(String[] args) {

	}
}
