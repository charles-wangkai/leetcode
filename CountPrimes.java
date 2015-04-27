import java.util.Arrays;

public class CountPrimes {
	public int countPrimes(int n) {
		if (n < 2) {
			return 0;
		}

		boolean[] primes = new boolean[n];
		Arrays.fill(primes, true);
		primes[0] = false;
		primes[1] = false;
		for (int i = 2; i * i < primes.length; i++) {
			if (primes[i]) {
				for (int j = i * i; j < primes.length; j += i) {
					primes[j] = false;
				}
			}
		}

		int primeNum = 0;
		for (boolean prime : primes) {
			if (prime) {
				primeNum++;
			}
		}
		return primeNum;
	}
}
