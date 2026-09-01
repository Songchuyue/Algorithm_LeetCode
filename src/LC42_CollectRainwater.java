public class LC42_CollectRainwater {
	public static int trap(int[] height) {
		int len = height.length;

		int lef = 0, rig = len - 1;
		int lefMax = height[lef], rigMax = height[rig];

		int ans = 0;

		while(lef < rig) {
			if(lefMax < rigMax) {
				ans += lefMax - height[lef];
				lef++;
				lefMax = Math.max(lefMax, height[lef]);
			} else {
				ans += rigMax - height[rig];
				rig--;
				rigMax = Math.max(rigMax, height[rig]);
			}
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