import java.util.HashMap;
import java.util.Map;

public class LC1_SumOfTwoNumber {
	public static int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> mp = new HashMap<Integer, Integer>();// 左值, 右下标

		for(int i = 0; i < nums.length; i++) {
			if(mp.containsKey(target - nums[i])) {
				return new int[]{mp.get(target - nums[i]), i};
			}
			mp.put(nums[i], i);
		}

		return new int[]{-1, -1};
	}

	public static void main(String[] args) {
		int[] nums = {2, 7, 11, 15};
		int target = 17;
		int[] ans = twoSum(nums, target);
		System.out.println(ans[0]);
		System.out.println(ans[1]);
	}
}
