import java.util.stream.IntStream;

public class Solution {
	public String gcdOfStrings(String str1, String str2) {
		String result = "";
		for (int length = 1; length <= Math.min(str1.length(), str2.length()); length++) {
			String x = str1.substring(0, length);

			if (isDivide(str1, x) && isDivide(str2, x)) {
				result = x;
			}
		}
		return result;
	}

	static boolean isDivide(String s, String x) {
		return s.length() % x.length() == 0
				&& IntStream.range(0, s.length()).allMatch(i -> s.charAt(i) == x.charAt(i % x.length()));
	}
}
