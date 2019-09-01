import java.util.stream.IntStream;

public class Solution {
	static final int MODULUS = 1_000_000_007;

	public int numPrimeArrangements(int n) {
		int primeCount = (int) IntStream.rangeClosed(1, n).filter(this::isPrime).count();

		return multiplyMod(factorialMod(primeCount), factorialMod(n - primeCount));
	}

	boolean isPrime(int x) {
		if (x == 1) {
			return false;
		}

		for (int i = 2; i * i <= x; i++) {
			if (x % i == 0) {
				return false;
			}
		}

		return true;
	}

	int multiplyMod(int x, int y) {
		return (int) ((long) x * y % MODULUS);
	}

	int factorialMod(int x) {
		return IntStream.rangeClosed(1, x).reduce(1, this::multiplyMod);
	}
}
