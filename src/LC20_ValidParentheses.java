import java.util.Stack;

public class LC20_ValidParentheses {
	public static boolean isValid(String s) {
		int length = s.length();

		Stack<Character> stack = new Stack<>();

		for(int i = 0; i < length; i++) {
			char c = s.charAt(i);

			if(c == '(' || c == '[' || c == '{') {
				stack.add(c);
			} else {
				if(stack.empty()) return false;

				char tem = stack.peek();
				// if(c != tem) return false;
				if(Math.abs(tem - c) > 2) return false;

				stack.pop();
			}
		}

		// if(!stack.empty()) return false;
		return stack.empty();
	}

	public static void main(String[] args) {
		String s = "()([]{})";

		System.out.println(isValid(s));
	}
}
