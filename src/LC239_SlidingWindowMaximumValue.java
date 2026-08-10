import java.util.ArrayDeque;
import java.util.Deque;

public class LC239_SlidingWindowMaximumValue {
	public static int[] maxSlidingWindow(int[] nums, int k) {
		int len = nums.length;
		int[] ans = new int[len - k + 1];

		Deque<Integer> dq = new ArrayDeque<>();
		for(int i = 0; i < k; i++) {
			while(!dq.isEmpty() && nums[dq.getLast()] < nums[i]) dq.removeLast();
			dq.addLast(i);
		}

		for(int i = 0; i <= len - k; i++) {
			while(!dq.isEmpty() && dq.getFirst() < i) dq.removeFirst();

			ans[i] = nums[dq.getFirst()];

			if(i == len - k) break;

			while(!dq.isEmpty() && nums[dq.getLast()] < nums[i + k]) dq.removeLast();
			dq.addLast(i + k);
		}

		return ans;
	}

	public static void main(String[] args) {
		int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
		int k = 3;

		int[] ans = maxSlidingWindow(nums, k);
		for(int i : ans) System.out.println(i);
	}
}
