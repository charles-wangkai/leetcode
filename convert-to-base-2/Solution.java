public class Solution {
	public String baseNeg2(int N) {
		String result = "";
		while (N != 0) {
			int digit = (N % 2 == 0) ? 0 : 1;

			result = digit + result;
			N = (N - digit) / (-2);
		}
		return result.isEmpty() ? "0" : result;
	}
}
