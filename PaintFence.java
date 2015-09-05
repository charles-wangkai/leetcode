public class PaintFence {
	public int numWays(int n, int k) {
		if (k == 0) {
			return 0;
		}
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return k;
		}

		int prev = k;
		int current = k * k;
		for (int i = 0; i < n - 2; i++) {
			int next = (current + prev) * (k - 1);

			prev = current;
			current = next;
		}
		return current;
	}
}
