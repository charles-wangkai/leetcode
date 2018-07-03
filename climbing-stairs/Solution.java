public class Solution {
	public int climbStairs(int n) {
		if (n < 3) {
			return n;
		}
		int previous = 1;
		int current = 2;
		for (int i = 3; i <= n; i++) {
			int nextCurrent = previous + current;
			previous = current;
			current = nextCurrent;
		}
		return current;
	}
}
