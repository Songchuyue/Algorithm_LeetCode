public class LC35_SearchInsertionPosition {
	public static int searchInsert(int[] nums, int target) {
		// 注意 找不到分为, 头插, 中插, 尾插
		int len = nums.length;

		for(int i = 0; i < len; i++) {
			if(nums[i] >= target) return i;
		}

		return len;
	}

	public static void main(String[] args) {
		int[] nums = {1,3,5,6};

		int ans = searchInsert(nums, 7);

		System.out.println(ans);
	}
}
