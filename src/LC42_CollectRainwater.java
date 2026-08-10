public class LC42_CollectRainwater {
	public static int trap(int[] height) {
		int len = height.length;

		int[] lef = new int[len];
		int[] rig = new int[len];
//		int[] ans = new int[len];
		int ans = 0;

		lef[0] = height[0];
		for(int i = 1; i < len; i++) {
			lef[i] = Math.max(lef[i - 1], height[i]);
		}

		rig[len - 1] = height[len - 1];
		for(int i = len - 2; i >= 0; i--) {
			rig[i] = Math.max(rig[i + 1], height[i]);
		}

		for(int i = 0; i < len; i++) {
			// ans[i] = Math.max(0, Math.min(lef[i], rig[i]) - height[i]);
			ans += Math.max(0, Math.min(lef[i], rig[i]) - height[i]);
		}

		return ans;
	}

	public static void main(String[] args) {
		int[] height = new int[]{5,0,3};

		System.out.println(trap(height));
	}
}

/**
 * 1, 分为lef和rig两个数组
 * 2, 以lef为例, lef[i]记录了从0 ~ i, 最大的height. rig同理
 * 3, 则ans[i] = max(0, min(lef[i], rig[i]) - height[i])
 */