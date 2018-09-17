import java.util.ArrayList;
import java.util.List;

public class Solution {
	static List<Long> superPalindromes = buildSuperPalindromes();

	public int superpalindromesInRange(String L, String R) {
		long lower = Long.parseLong(L);
		long upper = Long.parseLong(R);
		int result = 0;
		for (long superPalindrome : superPalindromes) {
			if (superPalindrome >= lower && superPalindrome <= upper) {
				result++;
			}
		}
		return result;
	}

	static List<Long> buildSuperPalindromes() {
		List<Long> superPalindromes = new ArrayList<>();
		for (int half = 1; half < 100_000; half++) {
			checkAndAdd(superPalindromes, buildPalindromeWithoutLast(half));

			if (half < 10000) {
				checkAndAdd(superPalindromes, buildPalindromeWithLast(half));
			}
		}
		return superPalindromes;
	}

	static int buildPalindromeWithoutLast(int half) {
		return Integer.parseInt(
				String.valueOf(half) + new StringBuilder(String.valueOf(half)).reverse().substring(1).toString());
	}

	static int buildPalindromeWithLast(int half) {
		return Integer.parseInt(String.valueOf(half) + new StringBuilder(String.valueOf(half)).reverse().toString());
	}

	static void checkAndAdd(List<Long> superPalindromes, int root) {
		long square = (long) root * root;
		if (isPalindrome(square)) {
			superPalindromes.add(square);
		}
	}

	static boolean isPalindrome(long n) {
		return n == Long.parseLong(new StringBuilder(String.valueOf(n)).reverse().toString());
	}
}
