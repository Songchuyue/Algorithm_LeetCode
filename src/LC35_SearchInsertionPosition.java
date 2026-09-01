public class LC35_SearchInsertionPosition {
	public static int searchInsert(int[] nums, int target) {
		// 注意 找不到分为, 头插, 中插, 尾插
		int len = nums.length;

		int lef = 0, rig = len - 1, mid = (lef + rig) / 2;

		while(lef <= rig) {
			if(nums[mid] == target) return mid;
			else if(nums[mid] < target) {// 插入位置只可能在mid右边
				if(mid == len - 1 || nums[mid + 1] >= target) return mid + 1;// mid已经是最右边 或 mid右边的值不小于目标值

				lef = mid + 1;
			}
			else if(nums[mid] > target) {// 插入位置在mid左边 或 mid本身
				if(mid == 0 || nums[mid - 1] < target) return mid;// mid已经是最左边 或 mid左边的值小于目标值
				else if(nums[mid - 1] == target) return mid - 1;

				rig = mid - 1;
			}

			mid = (lef + rig) / 2;
		}

		return -1;
	}

	public static void main(String[] args) {
		int[] nums = {1,3,5,6};

		int ans = searchInsert(nums, 7);

		System.out.println(ans);
	}
}
