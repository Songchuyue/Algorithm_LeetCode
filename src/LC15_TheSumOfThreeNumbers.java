import java.util.*;

public class LC15_TheSumOfThreeNumbers {
	public static List<List<Integer>> threeSum(int[] nums) {
		int len = nums.length;
		Arrays.sort(nums);

		List<List<Integer>> ans = new ArrayList<>();

		for(int i = 0; i + 2 < len; i++) {
			if(nums[i] > 0) break;
			if(i > 0 && nums[i - 1] == nums[i]) continue;

			// 下面的循环负责双指针寻找所有的匹配项
			for(int lef = i + 1, rig = len - 1; lef < rig;) {
				if(nums[i] + nums[lef] > 0) break;
				while(lef > i + 1 && lef < rig && nums[lef - 1] == nums[lef]) lef++;
				while(rig < len - 1 && lef < rig && nums[rig] == nums[rig + 1]) rig--;

				if(lef >= rig) break;

				int sum = nums[i] + nums[lef] + nums[rig];
				if(sum == 0) {
					List<Integer> tmp = new ArrayList<>();
					tmp.add(nums[i]); tmp.add(nums[lef]); tmp.add(nums[rig]);
					ans.add(tmp);
					lef++;
					rig--;
				} else if(sum < 0) {
					lef++;
				} else {
					rig--;
				}
			}
		}

		return ans;
	}

	public static void main(String[] args) {
		int[] nums = new int[]{-1,0,1,2,-1,-4};

		List<List<Integer>> ans = threeSum(nums);

		for(List<Integer> i : ans) {
			for(Integer j : i) {
				System.out.print(j);
				System.out.print(' ');
			}
			System.out.println();
		}
	}
}
