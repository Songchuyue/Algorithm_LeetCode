import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class LC51_NQueens {
	private static List<List<String>> ans;

	private static void dfs(int[] existingQueenPlaces, int depth, int n) {
		int[] candidatePlace = new int[n + 1];// 初始为0, 不合格为-1

		// 计算当前层哪些位置可以放置
		if(depth == 1) {// 若在第一层, 则所有位置均可
			;
		} else {// depth >= 2
			for(int i = 1; i < depth; i++) {// 从existingQueenPlaces[1]到existingQueenPlaces[depth - 1]逐层排除
				int existingQueenPlace = existingQueenPlaces[i];

				candidatePlace[existingQueenPlace] = -1;

				int distance = depth - i;
				int leftIllegalPlace = existingQueenPlace - distance;
				int rightIllegalPlace = existingQueenPlace + distance;

				if(leftIllegalPlace >= 1) candidatePlace[leftIllegalPlace] = -1;
				if(rightIllegalPlace <= n) candidatePlace[rightIllegalPlace] = -1;
			}
		}// 该循环结束, candidatePlace中记录了本层所有可以放置棋子的位置

		Queue<Integer> allLegalPlace = new ArrayDeque<>();// 用队列来记录位置

		for(int i = 1; i <= n; i++) {
			if(candidatePlace[i] == 0) {
				allLegalPlace.add(i);
			}
		}

		if(allLegalPlace.isEmpty()) {// 无可用位置, 直接返回
			return;
		}

		// 如果目前已经是最后一层, 则可以写答案
		if(depth == n) {
			while(!allLegalPlace.isEmpty()) {
				existingQueenPlaces[depth] = allLegalPlace.poll();

				List<String> oneAnswer = new ArrayList();

				for (int i = 1; i <= n; i++) {// 逐层写String
					StringBuilder sb = new StringBuilder(n);

					for (int j = 1; j <= n; j++) {
						sb.append(existingQueenPlaces[i] == j ? 'Q' : '.');
					}

					oneAnswer.add(sb.toString());
				}

				ans.add(oneAnswer);
			}

			return;
		}

		// 现在对队列中存在的位置进行下一层遍历
		while(!allLegalPlace.isEmpty()) {
			existingQueenPlaces[depth] = allLegalPlace.poll();

			dfs(existingQueenPlaces, depth + 1, n);
		}
	}

	private static List<List<String>> solveNQueens(int n) {
		ans = new ArrayList<>();

		int[] existingQueenPlaces = new int[n + 1];

		for(int i = 0; i <= n; i++) existingQueenPlaces[i] = 0;

		dfs(existingQueenPlaces, 1, n);

		/*
		 * 首先, 不是只找出一个方案, 需要找出所有方案
		 * 只遍历一遍, 相当于只找了一个方案
		 *
		 * 有一个初步想法, 现在来完善一下
		 * 1, 一个长度为n或者n + 1的数组, 用来记录每一层放棋子的坐标(1 ~ n)
		 * 2, 设计一个函数, 能根据上述的数组, 目前的深度, 算出当前所在层可以放置棋子的全部位置, 使用队列保存
		 * 3, 若队列为空, 表示失败, 应该返回? 若队列不为空, 则应该逐个弹出并处理
		 * 4, 通过返回null表示失败? 还是用一个类似全局变量的东西来记录正确答案? 是否可以用类变量实现?
		 */

		return ans;
	}

	public static void main(String[] args) {

	}
}
