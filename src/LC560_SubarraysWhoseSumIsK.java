import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC560_SubarraysWhoseSumIsK {
	public static int subarraySum(int[] nums, int k) {
		int len = nums.length;
		int sum = 0;
		int ans = 0;
		Map<Integer, Integer> mp = new HashMap<>();

		sum += nums[0];
		if(sum == k) ans++;
		mp.put(sum, 1);

		for(int i = 1; i < len; i++) {
			sum += nums[i];

			if(sum == k) {
				ans++;
			}

			int tar = sum - k;
			if(mp.containsKey(tar)) {
				ans += mp.get(tar);
			}

			if(!mp.containsKey(sum)) {
				mp.put(sum, 1);
			} else {
				mp.put(sum, mp.get(sum) + 1);
			}
		}

		return ans;
	}

	public static void main(String[] args) {
		int[] nums = new int[]{1,-2,3,4,-2,-3,7};
		int k = 2;

		System.out.println(subarraySum(nums, k));
	}
}
