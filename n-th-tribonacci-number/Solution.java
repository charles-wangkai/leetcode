public class Solution {
	public int tribonacci(int n) {
		if (n <= 1) {
			return n;
		}

		int prevPrev = 0;
		int prev = 1;
		int curr = 1;
		for (int i = 0; i < n - 2; i++) {
			int next = prevPrev + prev + curr;

			prevPrev = prev;
			prev = curr;
			curr = next;
		}

		return curr;
	}
}
