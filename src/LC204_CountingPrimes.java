import java.util.ArrayList;

public class LC204_CountingPrimes {
	public static int countPrimes(int n) {
		if(n <= 2) return 0;

		int cnt = 1;

		boolean[] primes = new boolean[n + 1];

		for(int i = 0; i < primes.length; i++) {
			primes[i] = true;
		}

		for(int i = 3; i < n; i += 2) {// 只考虑从3开始的奇数
			if(primes[i]) {
				cnt++;
			}

			for(int j = 3; i * j <= n; j += 2) {
				primes[i * j] = false;
			}
		}

		return cnt;
	}

	public static void main(String[] args) {
		System.out.println(countPrimes(100));
	}
}
