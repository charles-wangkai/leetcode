public class PreimageSizeOfFactorialZeroesFunction {
	public int preimageSizeFZF(int K) {
		long min = find(K, (k, value) -> value >= k);
		if (min < 0) {
			return 0;
		}

		long max = find(K, (k, value) -> value > k);
		return (int) (max - min + 1);
	}

	long find(int K, GoLeftChecker goLeftchecker) {
		long lower = 0;
		long upper = 5000000000L;
		long result = -1;
		while (lower <= upper) {
			long middle = (lower + upper) / 2;
			long tailingZeroNum = computeTailingZeroNum(middle);
			if (tailingZeroNum == K) {
				result = middle;
			}

			if (goLeftchecker.check(K, tailingZeroNum)) {
				upper = middle - 1;
			} else {
				lower = middle + 1;
			}
		}
		return result;
	}

	long computeTailingZeroNum(long N) {
		long tailingZeroNum = 0;
		while (N != 0) {
			tailingZeroNum += N / 5;
			N /= 5;
		}
		return tailingZeroNum;
	}
}

interface GoLeftChecker {
	boolean check(int K, long value);
}