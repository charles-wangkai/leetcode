public class Solution {
	public int lastRemaining(int n) {
		return search(n, true);
	}

	int search(int n, boolean leftOrRight) {
		return n == 1 ? 1 : search(n / 2, !leftOrRight) * 2 - (!leftOrRight && n % 2 == 0 ? 1 : 0);
	}
}
