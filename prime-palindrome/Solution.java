public class Solution {
	public int primePalindrome(int N) {
		for (int length = 1;; length++) {
			int result = search(N, length);
			if (result > 0) {
				return result;
			}
		}
	}

	int search(int N, int length) {
		int halfLength = (length + 1) / 2;
		int upper = pow10(halfLength);
		for (int half = 1; half < upper; half++) {
			int palindrome = buildPalindrome(half, length % 2 == 0);
			if (palindrome >= N && isPrime(palindrome)) {
				return palindrome;
			}
		}
		return -1;
	}

	int pow10(int exponent) {
		int result = 1;
		for (int i = 0; i < exponent; i++) {
			result *= 10;
		}
		return result;
	}

	int buildPalindrome(int half, boolean evenLength) {
		return Integer.parseInt(String.valueOf(half)
				+ new StringBuilder(evenLength ? String.valueOf(half) : (half < 10 ? "" : String.valueOf(half / 10)))
						.reverse().toString());
	}

	boolean isPrime(int n) {
		if (n < 2) {
			return false;
		}

		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
}
