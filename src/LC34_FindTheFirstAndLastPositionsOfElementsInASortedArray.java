public class LC34_FindTheFirstAndLastPositionsOfElementsInASortedArray {
	public static int[] searchRange(int[] nums, int target) {
		int len = nums.length;

		// 1, 排除特殊情况

		// len == 0
		if(len == 0) return new int[]{-1, -1};

		// len == 1
		if(len == 1) return nums[0] == target ? new int[]{0, 0} : new int[]{-1, -1};

		// 无 target
		int oneIndexOfTarget = -1;
		for(int lef = 0, rig = len - 1; lef <= rig;) {// 一定能收敛至lef == rig
			int mid = lef + ((rig - lef) >> 1);

			// if(lef >= rig && nums[lef] != target) return new int[]{-1, -1};

			if(nums[mid] < target) lef = mid + 1;
			else if(nums[mid] > target) rig = mid - 1;
			else {
				oneIndexOfTarget = mid;
				break;// 仅在能找到时推出循环
			}
		}

		if(oneIndexOfTarget == -1) return new int[]{-1, -1};

		// 2, 已知len >= 2, 且target存在

		int leftBoundary = -1, rightBoundary = len;

		// 找最大的小于 target 的元素
		if(nums[0] != target) {
			for(int lef = 0, rig = oneIndexOfTarget - 1; lef <= rig; ) {
				int mid = lef + ((rig - lef) >> 1);

				if(nums[mid] == target) rig = mid - 1;
				else {
					leftBoundary = mid;
					lef = mid + 1;
				}
			}
		}

		// 找最小的大于 target 的元素
		if(nums[len - 1] != target) {
			for(int lef = oneIndexOfTarget + 1, rig = len - 1; lef <= rig;) {
				int mid = lef + ((rig - lef) >> 1);

				if(nums[mid] == target) lef = mid + 1;
				else {
					rightBoundary = mid;
					rig = mid - 1;
				}
			}
		}

		return new int[]{++leftBoundary, --rightBoundary};
	}

	public static void main(String[] args) {
		int[] nums = new int[]{1,2,3,3,3,3,4,5,9};
		int target = 3;
		int[] ans = searchRange(nums, target);
		System.out.println(ans[0] + " " + ans[1]);
	}
}
