import java.util.Arrays;

public class Solution {
	public int monotoneIncreasingDigits(int N) {
		if (N == 0) {
			return 0;
		}

		String s = String.valueOf(N);
		char first = s.charAt(0);
		String lower = first + repeat(first, s.length() - 1);
		if (Integer.parseInt(lower) <= N) {
			int high = Integer.parseInt(first + repeat('0', s.length() - 1));
			return high + monotoneIncreasingDigits(N - high);
		} else if (first == '1') {
			return Integer.parseInt(repeat('9', s.length() - 1));
		} else {
			return Integer.parseInt((char) (first - 1) + repeat('9', s.length() - 1));
		}
	}

	String repeat(char ch, int count) {
		char[] chs = new char[count];
		Arrays.fill(chs, ch);
		return new String(chs);
	}
}
