import java.util.Arrays;

public class LC16_TheClosestSumOfThreeNumbers {
	public static int threeSumClosest(int[] nums, int target) {
		int len = nums.length;
		Arrays.sort(nums);

		int ans = 100000005;

		for(int i = 0; i + 2 < len; i++) {
			if(i > 0 && nums[i - 1] == nums[i]) continue;

			// 下面的循环负责双指针寻找所有的匹配项
			for(int lef = i + 1, rig = len - 1; lef < rig;) {
				while(lef > i + 1 && lef < rig && nums[lef - 1] == nums[lef]) lef++;
				while(rig < len - 1 && lef < rig && nums[rig] == nums[rig + 1]) rig--;

				if(lef >= rig) break;

				int sum = nums[i] + nums[lef] + nums[rig];
				if(Math.abs(target - sum) < Math.abs(target - ans)) ans = sum;

				if(sum == target) {
					return target;
				} else if(sum < target) {
					lef++;
				} else {
					rig--;
				}
			}
		}

		return ans;
	}

	public static void main(String[] args) {
		int[] nums = new int[]{-5,-5,-4,0,0,3,3,4,5};
		int target = -2;

		System.out.println(threeSumClosest(nums, target));
	}
}
