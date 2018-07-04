public class Solution {
	public boolean isSelfCrossing(int[] x) {
		if (x.length < 4) {
			return false;
		}

		int prev1 = x[2];
		int prev2 = x[1];
		int prev3 = x[0];
		int prev4 = 0;
		boolean increasing = x[2] > x[0];

		for (int i = 3; i < x.length; i++) {
			if (increasing) {
				if (x[i] <= prev2) {
					increasing = false;

					if (x[i] + prev4 >= prev2) {
						prev1 -= prev3;
					}
				}
			} else {
				if (x[i] >= prev2) {
					return true;
				}
			}

			prev4 = prev3;
			prev3 = prev2;
			prev2 = prev1;
			prev1 = x[i];
		}
		return false;
	}
}
