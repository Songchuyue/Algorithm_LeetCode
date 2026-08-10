import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC18_TheSumOfFourNumbers {
	public static List<List<Integer>> fourSum(int[] nums, int target) {
		int len = nums.length;
		Arrays.sort(nums);
		List<List<Integer>> ans = new ArrayList<>();

		for(int i = 0; i + 3< len; i++) {
			if((long)nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > (long)target) break;
			if((long)nums[i] + nums[len - 1] + nums[len - 2] + nums[len - 3] < (long)target) continue;

			// 每次进入该循环, 都需要确保nums[i]枚举的是新元素
			while(i > 0 && i + 3 < len && nums[i - 1] == nums[i]) i++;

			for(int j = i + 1; j + 2 < len; j++) {
				if((long)nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > (long)target) break;
				if((long)nums[i] + nums[j] + nums[len - 1] + nums[len - 2] < (long)target) continue;

				// 每次进入该循环, 都需要确保nums[j]枚举的是新元素
				while(j > i + 1 && j + 2 < len && nums[j - 1] == nums[j]) j++;

				// 双指针找全体值
				for(int lef = j + 1, rig = len - 1; lef < rig;) {
					while (lef > j + 1 && lef < rig && nums[lef - 1] == nums[lef]) lef++;
					while (rig < len - 1 && lef < rig && nums[rig] == nums[rig + 1]) rig--;
					if (lef >= rig) break;

					long sum = (long)nums[i] + nums[j] + nums[lef] + nums[rig];

					if (sum == (long)target) {
						List<Integer> tem = new ArrayList<>();
						tem.add(nums[i]);
						tem.add(nums[j]);
						tem.add(nums[lef]);
						tem.add(nums[rig]);
						ans.add(tem);
						lef++;
						rig--;
					} else if (sum < target) {
						lef++;
					} else {
						rig--;
					}
				}
			}
		}

		return ans;
	}

	public static void main(String[] args) {
		int[] nums = new int[]{1,0,-1,0,-2,2};
		int target = 0;

		List<List<Integer>> ans = fourSum(nums, target);
		for(List<Integer> i : ans) {
			for(int j : i) {
				System.out.print(j);
				System.out.print(' ');
			}
			System.out.println();
		}
	}
}
