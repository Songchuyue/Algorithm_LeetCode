import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC438_FindAllAnagramsInTheString {
	public static List<Integer> findAnagrams(String s, String p) {
		List<Integer> ans = new ArrayList<>();

		// 记录 p 中各字母的频率
		int lenP = p.length();
		char[] strP = p.toCharArray();
		int[] windP = new int[26];
		for(int i = 0; i < lenP; i++) {
			windP[strP[i] - 'a']++;
		}

		// 维护长度为 p.length() 的滑动窗口
		int lenS = s.length();
		char[] strS = s.toCharArray();
		int[] windS = new int[26];

		// 若s.length() < p.length(), 直接退出
		if(lenS < lenP) return ans;// ans 为空

		// 维护一个初始的滑动窗口, 窗口大小为 lenP
		int lef = 0, rig = 0;
		while(rig < lenP) {
			windS[strS[rig++] - 'a']++;
		}

		// 记录不匹配的字母个数
		int diff = 0;
		for(int i = 0; i < 26; i++) {
			if(windS[i] != windP[i]) {
				diff++;
			}
		}

		// 进入循环先判断滑动窗口是否匹配, 然后右移
		while(rig <= lenS) {
			if(diff == 0) ans.add(lef);

			if(rig == lenS) break;

			int lefC = strS[lef++] - 'a';
			int rigC = strS[rig++] - 'a';

			if(lefC == rigC) continue;

			windS[lefC]--;
			windS[rigC]++;

			if(windS[lefC] == windP[lefC]) diff--;
			else if(windS[lefC] + 1 == windP[lefC]) diff++;

			if(windS[rigC] == windP[rigC]) diff--;
			else if(windS[rigC] - 1 == windP[rigC]) diff++;
		}

		return ans;
	}

	public static void main(String[] args) {
		String s = "cbaebabacd";
		String p = "abc";
		List<Integer> ans = findAnagrams(s, p);
		for(int i : ans) {
			System.out.println(i);
		}
	}
}
