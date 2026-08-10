public class LC31_NextPermutation {
	public static void nextPermutation(int[] nums) {
		int len = nums.length;

		// 从后往前找第一个逆序
		int arc = len - 1;
		while(arc > 0 && nums[arc - 1] >= nums[arc]) arc--;// 非严格递增

		// 此时arc停留在, 从右往左的非严格递增序列的左端

		// 若arc == 0, 表示nums原本就说非严格递减序列, 逆置即可
		// 若arc > 0, 则从nums[arc] ~ nums[len - 1]有序, 而nums[arc - 1]是逆序元
		// 2 4 3 2 1 -> 3 1 2 2 4
		if(arc > 0) {
			for (int i = len - 1; i >= arc; i--) {// 找第一个比逆序元大的元素, 并交换
				if (nums[i] > nums[arc - 1]) {
					int tem = nums[i];
					nums[i] = nums[arc - 1];
					nums[arc - 1] = tem;
					break;
				}
			}
		}

		for(int lef = arc, rig = len - 1; lef < rig; lef++, rig--) {
			int tem = nums[lef];
			nums[lef] = nums[rig];
			nums[rig] = tem;
		}
	}

	public static void main(String[] args) {
		int[] nums = new int[]{1, 4, 6, 5, 3, 2};
		nextPermutation(nums);
		for(int i : nums) {
			System.out.print(i);
			System.out.print(' ');
		}
	}
}