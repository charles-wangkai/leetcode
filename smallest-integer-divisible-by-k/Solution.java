public class Solution {
	public int smallestRepunitDivByK(int K) {
		boolean[] visited = new boolean[K];
		int remainder = 0;
		for (int length = 1;; length++) {
			remainder = (remainder * 10 + 1) % K;

			if (remainder == 0) {
				return length;
			}

			if (visited[remainder]) {
				return -1;
			}
			visited[remainder] = true;
		}
	}
}
