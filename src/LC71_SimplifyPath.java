import java.util.ArrayDeque;
import java.util.Deque;

public class LC71_SimplifyPath {
	private static String simplifyPath(String path) {
		int length = path.length();
		int pointer = 0;

		Deque<String> deque = new ArrayDeque<>();

		/*
		 * 可能遇到: / // /// . .. ... .... a-z A-Z 0-9 _
		 * 遇到'/', 指针右移至首个非'/'位置
		 * 遇到'.', 需要考虑单个. 俩个. 多个.
		 * 遇到正常文本, 直接操作就行
		 */

		while(pointer < length) {
			// 处理"/ // /// ////"
			while(pointer < length && path.charAt(pointer) == '/') {
				pointer++;
			}

			// 处理". .. ... ...."
			if(pointer < length && path.charAt(pointer) == '.') {
				if(pointer + 1 == length || path.charAt(pointer + 1) == '/') { // 单个.
					pointer++;
					continue;
				} else if(path.charAt(pointer + 1) == '.') { // 俩个.及以上
					if(pointer + 2 == length || path.charAt(pointer + 2) == '/') { // 只有俩个.
						if(!deque.isEmpty()) {
							deque.pollLast();
						}
						pointer += 2;
						continue;
					} else { // 三个.及以上
						StringBuilder sb = new StringBuilder();

						while(pointer < length && path.charAt(pointer) != '/') {
							sb.append(path.charAt(pointer));
							pointer++;
						}

						deque.offerLast(sb.toString());

						continue;
					}
				}
			}

			if(pointer >= length) break;

			// 处理正常文本
			StringBuilder sb = new StringBuilder();

			while(pointer < length && path.charAt(pointer) != '/') {
				sb.append(path.charAt(pointer));
				pointer++;
			}

			deque.offerLast(sb.toString());
		}

		StringBuilder sb = new StringBuilder();

		while(!deque.isEmpty()) {
			sb.append("/");
			sb.append(deque.pollFirst());
		}

		if(sb.isEmpty()) sb.append("/");

		return sb.toString();
	}

	public static void main(String[] args) {
		String path = "/123/abc//.///error/..";

		System.out.println(simplifyPath(path));
	}
}
