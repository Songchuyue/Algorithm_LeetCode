import java.util.*;

public class LC102_LevelOrderTraversalOfABinaryTree {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	/*
	 * 大致思路为, 用栈保存下一层的全部节点
	 * 如何区分不同层呢?
	 * 双栈?
	 * 记录数量?
	 */

	public List<List<Integer>> levelOrder(TreeNode root) {
		Queue<TreeNode> queue = new ArrayDeque<>();
		List<List<Integer>> ans = new ArrayList<>();

		if(root == null) return ans;
		ans.add(new ArrayList<>());

		TreeNode cur = root;
		queue.add(cur);

		int currentLayer = 0;
		int countOfCurrentLayer = 1;

		while(!queue.isEmpty()) {
			cur = queue.remove();

			ans.get(currentLayer).add(cur.val);

			if(cur.left != null) queue.add(cur.left);
			if(cur.right != null) queue.add(cur.right);

			countOfCurrentLayer--;
			if(countOfCurrentLayer <= 0 && !queue.isEmpty()) {// 当前层处理完毕, 进行收尾和铺垫
				currentLayer++;
				countOfCurrentLayer = queue.size();
				ans.add(new ArrayList<>());
			}
		}

		return ans;
	}
}
