import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LC3_LongestSubstringWithoutRepeatingCharacters {
	public static int lengthOfLongestSubstring(String s) {
		int len = s.length();
		char[] str = s.toCharArray();
		int lef = 0, ans = 0;
		Set<Character> st = new HashSet<>();

		for(int i = 0; i < len; i++) {
			if(st.contains(str[i])) {
				do{
					st.remove(str[lef]);
				} while(str[lef++] != str[i]);

			}
			st.add(str[i]);
			ans = Math.max(ans, i + 1 - lef);
		}

		return ans;
	}

	public static int lengthOfLongestSubString_Set(String s) {
		int length = s.length();

		int left = 0, right = 0, maxLen = 0;

		Set<Character> set = new HashSet<>();

		for(; right < length; right++) {
			char c = s.charAt(right);

			while(set.contains(c)) {
				set.remove(s.charAt(left++));
			}

			set.add(c);

			maxLen = Math.max(maxLen, right - left + 1);
		}

		return maxLen;
	}

	public static int lengthOfLongestSubString_Map(String s) {
		int length = s.length();

		int left = 0, right = 0, maxLen = 0;

		Map<Character, Integer> map = new HashMap<>();

		for(; right < length; right++) {
			char c = s.charAt(right);

			if(map.containsKey(c)) {
				left = Math.max(left, map.get(c) + 1);
			}

			map.put(c, right);

			maxLen = Math.max(maxLen, right - left + 1);
		}

		return maxLen;
	}

	public static void main(String[] args) {
		String s = "abcadeafg";

		System.out.println(lengthOfLongestSubstring(s));
		System.out.println(lengthOfLongestSubString_Set(s));
		System.out.println(lengthOfLongestSubString_Map(s));
	}
}
