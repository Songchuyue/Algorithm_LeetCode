public class LC26_RemoveDuplicatesFromASortedArray {
	public static int removeDuplicates(int[] nums) {
		int cnt = 0;

		int len = nums.length;

		for(int i = 0; i < len;) {
			nums[cnt++] = nums[i++];

			while(i < len && nums[i - 1] == nums[i]) i++;
		}

		return cnt;
	}

	public static void main(String[] args) {
		int[] nums = {0,0,1,1,1,2,2,3,3,4,4,5,5,6,6,6,7,7,7,7,7,7,8};

		int ans = removeDuplicates(nums);

		for(int i = 0; i < ans; i++) System.out.print(nums[i] + " ");
	}
}
