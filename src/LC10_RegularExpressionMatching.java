public class LC10_RegularExpressionMatching {
	public static boolean isMatch(String s, String p) {
		int len_s = s.length(), len_p = p.length();

		int i = 0, j = 0;

		while(i < len_s && j < len_p) {
			char cs = s.charAt(i), cp = p.charAt(j);

			if(cp == '.') { // .
				if(j + 1 == len_p || p.charAt(j + 1) != '*') { // 以.结尾或者.[a-z]
					i++; j++;
				} else { // .*
					if(j + 2 == len_p) return true; // 以.*结尾
					else { // .*[a-z]+
						j += 2;
						for(int k = i; k <= len_s; k++) {// 直接绕过.*进行匹配
							if(isMatch(s.substring(k, len_s), p.substring(j, len_p))) return true;
						}
						return false;
					}
				}
			} else if(j + 1 < len_p && p.charAt(j + 1) == '*') {// [a-z]*问题
				if(j + 2 == len_p) {// 以[a-z]*结尾
					for(int k = i; k < len_s; k++) {
						if(s.charAt(k) != p.charAt(j)) return false;
					}
					return true;
				} else {// [a-z]*[a-z]+
					if(isMatch(s.substring(i, len_s), p.substring(j + 2, len_p))) return true;// 假设[a-z]*应为空

					for (int k = i; k < len_s && s.charAt(k) == p.charAt(j); k++) {// 让[a-z]*模拟已匹配了1~x个字符, 再跳过[a-z]*
						if(isMatch(s.substring(k + 1, len_s), p.substring(j + 2, len_p))) return true;
					}
					return false;
				}
			} else if(cs == cp) {
				i++; j++;
			} else return false;
		}

		if(i == len_s) {
			while(j + 1 < len_p && p.charAt(j + 1) == '*') {
				j += 2;
			}
		}

		return i == len_s && j == len_p;
	}

	public static boolean isMatch_plus(String s, String p) {
		Boolean[][] memo = new Boolean[s.length() + 1][p.length() + 1];

		return isMatch_plus_dfs(s, 0, p, 0, memo);
	}

	private static boolean isMatch_plus_dfs(String s, int i, String p, int j, Boolean[][] memo) {
		if(memo[i][j] != null) return memo[i][j];

		if(j == p.length()) return memo[i][j] = (i == s.length());

		boolean ans = false;

		boolean firstMatch = (i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'));

		// 1, end
		// 2, 非*
		// 3, *
//		if(j + 1 >= p.length()) ans = firstMatch && isMatch_plus_dfs(s, i + 1, p, j + 1, memo);
//		else if(p.charAt(j + 1) != '*') ans = firstMatch && isMatch_plus_dfs(s, i + 1, p, j + 1, memo);
//		else ans = (isMatch_plus_dfs(s, i, p, j + 2, memo) || (firstMatch && isMatch_plus_dfs(s, i + 1, p, j, memo)));
		if(j + 1 < p.length() && p.charAt(j + 1) == '*') ans = (isMatch_plus_dfs(s, i, p, j + 2, memo) || (firstMatch && isMatch_plus_dfs(s, i + 1, p, j, memo)));
		else ans = firstMatch && isMatch_plus_dfs(s, i + 1, p, j + 1, memo);

		memo[i][j] = ans;

		return ans;
	}

	public static void main(String[] args) {
		String s = "aab";
		String p = "c*a*b";

		System.out.println(isMatch_plus(s,p));
	}
}
