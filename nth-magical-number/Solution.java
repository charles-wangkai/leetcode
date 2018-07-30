public class Solution {
	public int nthMagicalNumber(int N, int A, int B) {
		long lower = 2;
		long upper = (long) N * Math.max(A, B);
		while (true) {
			long middle = (lower + upper) / 2;

			long sequence = computeSequence(middle, A, B);
			if (sequence < N) {
				lower = middle + 1;
			} else {
				if (sequence == N && isMagical(middle, A, B)) {
					return (int) (middle % 1000000007);
				}

				upper = middle - 1;
			}
		}
	}

	boolean isMagical(long x, int A, int B) {
		return x % A == 0 || x % B == 0;
	}

	long computeSequence(long x, int A, int B) {
		return x / A + x / B - x / lcm(A, B);
	}

	int lcm(int x, int y) {
		return x / gcd(x, y) * y;
	}

	int gcd(int x, int y) {
		return y == 0 ? x : gcd(y, x % y);
	}
}
