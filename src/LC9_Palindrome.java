public class LC9_Palindrome {
	public static boolean isPalindrome(int x) {
		if(x < 0) return false;

		int x_tem = x, y = 0;

		while(x_tem > 0) {
			y *= 10;
			y += x_tem % 10;
			x_tem /= 10;
		}

		return x == y;
	}

	public static boolean isPalindrome_plus(int x) {
		if(x < 0) return false;
		if(x > 0 && x % 10 == 0) return false;

		int y = 0;

		while(x > y) {
			y = y * 10 + x % 10;
			x /= 10;
		}

		// x      x     y
		// 123321 123   123
		// 12321  12    123
		// 456321 45    1236
		// 123456 123   456

		return x == y || x == y / 10;
	}

	public static void main(String[] args) {
		int x = -123434321;

		System.out.println(isPalindrome(x));
	}
}
