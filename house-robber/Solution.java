public class Solution {
	public int rob(int[] num) {
		int prev3 = 0;
		int prev2 = 0;
		int prev1 = 0;
		for (int number : num) {
			int current = Math.max(prev3, prev2) + number;
			prev3 = prev2;
			prev2 = prev1;
			prev1 = current;
		}
		return Math.max(prev2, prev1);
	}
}
