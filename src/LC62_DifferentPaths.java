public class LC62_DifferentPaths {
	public static int uniquePaths(int m, int n) {
		m--; n--;

		if(m * n == 0) return 1;

		if(m < n) {// make sure n is the miner number
			int tem = m;
			m = n;
			n = tem;
		}

		m += n;// C(n, m)

		long ans = 1;

		// C(n, m) = (m * (m - 1) * ... * (m - n + 1)) / (n * (n - 1) * ... * 1)
		for(int i = 1; i <= n; i++) {
			ans = ans * (m - n + i) / i;
		}

		return (int)ans;
	}

	public static void main(String[] args) {
		System.out.println(uniquePaths(3, 4));
	}
}
