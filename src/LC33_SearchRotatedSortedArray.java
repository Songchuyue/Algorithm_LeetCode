public class LC33_SearchRotatedSortedArray {
	public static int search(int[] nums, int target) {
		int len = nums.length;

		if(len == 1) return nums[0] == target ? 0 : -1;

		// k == 0
		if(nums[0] < nums[len - 1]) {// 增序数列, 直接二分查找
			for(int lef = 0, rig = len - 1; lef <= rig;) {
				int mid = lef + ((rig - lef) >> 1);

				if(nums[mid] < target) {
					lef = mid + 1;
				} else if(nums[mid] > target) {
					rig = mid - 1;
				} else {
					return mid;
				}
			}
			return -1;
		}

		// k != 0

		// 1, 首先找原nums[n - 1]
		int k = 0;
		for(int lef = 0, rig = len - 1; lef < rig;) {
			int mid = lef + ((rig - lef) >> 1);

			if(nums[mid] <= nums[len - 1]) {// nums[mid]属于右段
				rig = mid - 1;
			} else if(nums[mid] >= nums[0]) {// nums[mid]属于左段
				lef = mid;
			}

			if(lef == rig) {
				k = len - 1 - lef;
			} else if(lef == rig - 1) {// 相差1
				if(nums[lef] < nums[rig]) {// nums[rig]为原nums[n - 1]
					k = len - 1 - rig;
				} else {// nums[lef]为原nums[n - 1]
					k = len - 1 - lef;
				}
				break;
			}
		}

		// System.out.println(k);

		// 2, 查找
		for(int lef = 0, rig = len - 1; lef <= rig;) {
			int mid = lef + ((rig - lef) >> 1);
			int real_mid = (mid - k + len) % len;

			if(nums[real_mid] < target) {
				lef = mid + 1;
			} else if(nums[real_mid] > target) {
				rig = mid - 1;
			} else {
				return real_mid;
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		int[] nums = new int[]{6,7,8,1,2,3,4,5};
		int target = 0;

		for(int i = 1; i <= 8; i++) {
			target = i;
			System.out.println(search(nums, target));
		}

		// System.out.println(search(nums, target));
	}
}
/**
 * 1 2 4 5 - 3
 * 0 1 2 3 4 5 6 7
 * 6 7 8 1 2 3 4 5
 */