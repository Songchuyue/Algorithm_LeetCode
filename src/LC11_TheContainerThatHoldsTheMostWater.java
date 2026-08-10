public class LC11_TheContainerThatHoldsTheMostWater {
	public static int maxArea(int[] height) {
		int len = height.length;

		// 双指针, 贪心
		int left = 0, right = len - 1, cap = 0;

		while(left < right) {
			cap = Math.max(cap, (right - left) * Math.min(height[left], height[right]));

			if(height[left] < height[right]) left++;
			else right--;
		}

		return cap;
	}

	public static void main(String[] args) {
		int[] height = new int[]{1,8,6,2,5,4,8,3,7};

		System.out.println(maxArea(height));
	}
}
