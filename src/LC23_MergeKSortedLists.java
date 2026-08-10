public class LC23_MergeKSortedLists {
	static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	private static ListNode mergeKLists(ListNode[] lists) {

		int n = lists.length;

		if(n == 0) return null;
		if(n == 1) return lists[0];
		// n >= 2

		/*
		 * lists表的序号为: 0 1 2 ~ n - 1
		 *
		 * 若叶子结点数量为n, 一颗不存在度为一结点的完全二叉树满足: n0 = n2 + 1
		 * 故整棵树的结点数量为2 * n - 1
		 *
		 *                     0,   1,     2,   ...   n - 1
		 * 1, 2, 3, ... n - 1, n, n + 1, n + 2, ... 2 * n - 1
		 *
		 * 败者树维护非叶子节点即可
		 *
		 * 初始: 一颗完全二叉树, 叶子节点挂着长串链表
		 * 建堆: 从后往前建堆, 非叶子节点数量为n - 1, 即从n - 1到1
		 *      第n - 1个节点比较2 * n - 2(原n - 2)和2 * n - 1(原n - 1)
		 *      每次比较, 留下较大节点的序号, 然后将较小节点的序号上浮
		 * 循环: 根节点上浮的序号即最小节点的序号, 正常移除, 并从对应序号的列表里面取出新数据
		 */

		/* 堆, heap[1]是根节点, 而heap[0]是"根节点父节点"
		 * 注意, 1 ~ n - 1都是内部节点, 而n ~ 2 * n - 1是叶子节点, 对应链表
		 * heap[1] ~ heap[n - 1]存的是链表序号, 为0 ~ n - 1
		 */
		int[] heap = new int[n];

		for(int i = 0; i <= n - 1; i++) heap[i] = -1;

		ListNode head = new ListNode(-1);
		ListNode cur = head;

		for(int i = n - 1; i >= n / 2; i--) { // 第一步: 建堆
			// 考虑n = 3, n = 5的情况, 其中存在某一节点的出度为1
			int lefChi = 2 * i - n, rigChi = 2 * i + 1 - n;

			int winner; // 记录两个链表头节点的胜者(更小者)的链表序号

			if(i != n / 2 || n % 2 == 0) {
				if (lists[lefChi] == null) lists[lefChi] = new ListNode(100000);
				if (lists[rigChi] == null) lists[rigChi] = new ListNode(100000);

				if (lists[lefChi].val <= lists[rigChi].val) {
					winner = lefChi;
					heap[i] = rigChi; // 败者留下, 胜者上浮
				} else {
					winner = rigChi;
					heap[i] = lefChi;
				}
			} else {
				if (lists[rigChi] == null) lists[rigChi] = new ListNode(100000);

				if (lists[heap[i]].val <= lists[rigChi].val) {
					winner = heap[i];
					heap[i] = rigChi;
				} else {
					winner = rigChi;
				}
			}

			// 偶数则上浮, 奇数则写入
			int j = i;

			while(true) {// 在这个while循环里, 要将winner逐层上浮, 直至遇到空节点(winner可能会变换!)
				j /= 2;

				if(heap[j] == -1) {// 为空, 则将winner写入, 退出
					heap[j] = winner;
					break;
				}

				if(lists[winner].val > lists[heap[j]].val) {
					// swap(winner, heap[j]);
					int tmp = winner;
					winner = heap[j];
					heap[j] = tmp;
				}
			}
		}

		// 建堆完成, 此时各链表的头节点已在堆中找到了属于自己的位置, 如果该链表为空, 则记为100000(最大值), 当根节点的父节点也为100000时结束
		while(lists[heap[0]].val != 100000) {
			cur.next = lists[heap[0]];

			int i = heap[0];// 现在需要找到最小值所在链表, 找出下一个值, 然后执行上浮, 直至出现新最小值

			lists[i] = lists[i].next == null ? new ListNode(100000) : lists[i].next;

			cur = cur.next;
			cur.next = null;

			int j = i + n;// j本质是链表对应的节点序号

			while(j >= 1) {// 在该while循环中, i的值指的是上浮操作中最小值对应的链表序号
				j /= 2;

				if(lists[i].val > lists[heap[j]].val) {
					int tmp = i;
					i = heap[j];
					heap[j] = tmp;
				}
			}

			heap[j] = i;
		}

		return head.next;
	}

	public static void main(String[] args) {
		ListNode first = new ListNode(11);
		first.next = new ListNode(14);
		first.next.next = new ListNode(17);

		ListNode second = new ListNode(12);
		second.next = new ListNode(15);
		second.next.next = new ListNode(18);

		ListNode third = new ListNode(13);
		third.next = new ListNode(16);
		third.next.next = new ListNode(19);

		ListNode fourth = new ListNode(14);
		fourth.next = new ListNode(17);
		fourth.next.next = new ListNode(20);

		ListNode[] lists = new ListNode[]{first, second, third, fourth};

		mergeKLists(lists);
	}
}
