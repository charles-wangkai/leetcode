public class LargestPalindromeProduct {
	public int largestPalindrome(int n) {
		if (n == 1) {
			return 9;
		}

		int upper = pow10(n) - 1;
		int lower = pow10(n - 1);

		for (int leftHalf = (int) ((long) upper * upper / pow10(n));; leftHalf--) {
			long palindrome = generatePalindrome(leftHalf);

			for (int i = upper; i >= lower && (long) i * i >= palindrome; i--) {
				if (palindrome % i == 0) {
					return (int) (palindrome % 1337);
				}
			}
		}
	}

	long generatePalindrome(int leftHalf) {
		return Long.parseLong(leftHalf + new StringBuilder(String.valueOf(leftHalf)).reverse().toString());
	}

	int pow10(int exponent) {
		int result = 1;
		for (int i = 0; i < exponent; i++) {
			result *= 10;
		}
		return result;
	}
}