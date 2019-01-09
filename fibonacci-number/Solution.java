public class Solution {
	public int fib(int N) {
		if (N == 0) {
			return 0;
		}

		int prev = 0;
		int cur = 1;
		for (int i = 0; i < N - 1; i++) {
			int next = prev + cur;

			prev = cur;
			cur = next;
		}
		return cur;
	}
}
