public class LC14_LongestCommonPrefix {
	public static String longestCommonPrefix(String[] strs) {
		int len = strs.length;

		// 值得一提, 外层循环在遍历第一个字符串, 而内层循环在遍历各字符串
		for(int i = 0; i < strs[0].length(); i++) {
			char cha = strs[0].charAt(i);

			for(int j = 1; j < len; j++) {
				if(strs[j].length() <= i || strs[j].charAt(i) != cha) return strs[0].substring(0, i);
			}
		}

		return strs[0];
	}

	public static void main(String[] args) {
		String[] strs = {"flower", "flow", "flight"};

		String ans = longestCommonPrefix(strs);

		System.out.println(ans);
	}
}
