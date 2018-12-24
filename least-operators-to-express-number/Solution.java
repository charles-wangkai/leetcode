public class Solution {
	public int leastOpsExpressTarget(int x, int target) {
		int pos = -1;
		int neg = -1;
		int k = 0;
		while (target != 0) {
			int current = target % x;

			if (k == 0) {
				pos = current * 2;
				neg = (x - current) * 2;
			} else {
				int nextPos = Math.min(pos + current * k, neg + (current + 1) * k);
				int nextNeg = Math.min(pos + (x - current) * k, neg + (x - current - 1) * k);

				pos = nextPos;
				neg = nextNeg;
			}

			target /= x;
			k++;
		}
		return Math.min(pos - 1, k + neg - 1);
	}
}
