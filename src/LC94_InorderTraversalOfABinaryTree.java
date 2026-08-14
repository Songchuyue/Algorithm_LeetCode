import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC94_InorderTraversalOfABinaryTree {
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
	  * 中序遍历: 即左-根-右
	  * 第一种是递归, 处理左, 处理根, 处理右
	  * 还有一种是非递归:
	  *   1. 第一个节点可以通过root->left->...->left来找到
	  *   2. 第一个节点一定没有左子树, 所以只需考虑其右子树
	  *   3. 相当于找到第一个节点, 处理其右子树, 再处理第一个节点的父节点
	  *   4. 这样就可以使用栈来解决, 拿到根, 一直压栈, 直到没有左子树, 然后处理根, 处理右子树, 重复
	  *   5. 补充一个细节, 往左找的时候, 可以一直找到null, 或者一直找到cur.left为null
	  *   6. 再补充一个, 弹栈的节点必须是最新节点!
	  */

	// 非递归解法
	public List<Integer> inorderTraversal(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();

		List<Integer> ans = new ArrayList<>();

		TreeNode cur = root;

		while(cur != null) {
			stack.push(cur);
			cur = cur.left;
		}

		while(!stack.isEmpty()) {// 弹栈节点一定是当前最新节点, 即无左子树
			cur = stack.pop();// 处理当前节点
			ans.add(cur.val);

			cur = cur.right;// 处理右子树

			while(cur != null) {// 找到右子树的第一个节点
				stack.push(cur);
				cur = cur.left;
			}
		}

		return ans;
	}

	public static void main(String[] args) {

	}
}
