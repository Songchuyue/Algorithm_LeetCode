public class LC138_CopyingALinkedListWithRandomPointers {
	static class Node {
		int val;
		Node next;
		Node random;

		public Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}
	}

	private static Node copyRandomList(Node head) {
		if(head == null) return null;

		Node cur = head, nex;
		while(cur != null) {// cur -> nex
			nex = cur.next;
			cur.next = new Node(cur.val);
			cur.next.next = nex;// cur -> new -> nex
			cur = nex;
		}

		cur = head;
		while(cur != null) {
			nex = cur.next;
			if(cur.random != null) nex.random = cur.random.next;
			cur = nex.next;
		}

		cur = head;
		head = head.next;
		while(cur.next != null) {
			nex = cur.next;
			cur.next = nex.next;
			cur = nex;
		}

		return head;
	}
}
