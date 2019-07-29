public class Solution {
	public boolean isArmstrong(int N) {
		String s = String.valueOf(N);

		return s.chars().map(ch -> pow(ch - '0', s.length())).sum() == N;
	}

	int pow(int base, int exponent) {
		int result = 1;
		for (int i = 0; i < exponent; i++) {
			result *= base;
		}
		return result;
	}
}
