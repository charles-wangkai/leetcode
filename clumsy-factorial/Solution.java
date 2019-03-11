public class Solution {
	public int clumsy(int N) {
		int result = 0;
		for (int i = N; i >= 1; i -= 4) {
			result += computePart(i, i == N);
		}
		return result;
	}

	int computePart(int x, boolean first) {
		int result = first ? x : -x;
		if (x >= 2) {
			result *= x - 1;
		}
		if (x >= 3) {
			result /= x - 2;
		}
		if (x >= 4) {
			result += x - 3;
		}
		return result;
	}
}
