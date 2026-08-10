import java.util.*;

public class LC49_GroupingOfAnagrams {
	public static List<List<String>> groupAnagrams(String[] strs) {
		Map<String, List<String>> mp = new HashMap<>();

		for(String str : strs) {// 取出strs中的一个字符串
			char[] tmp = str.toCharArray();
			Arrays.sort(tmp);// tmp为排序后的字符串
			String key = new String(tmp);

			if(mp.containsKey(key)) {// mp中是否有tmp?
				mp.get(key).add(str);// 若有, 则直接插入
			} else {
				mp.put(key, new ArrayList<String>());// 若无, 则新建
				mp.get(key).add(str);
			}
		}

		return new ArrayList<>(mp.values());
	}

	public static void main(String[] args) {
		String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};

		List<List<String>> ans = groupAnagrams(strs);

		for(List<String> lisStr : ans) {
			System.out.println(lisStr);
		}
	}
}
