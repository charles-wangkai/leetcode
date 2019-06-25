public class Solution {
	public boolean carPooling(int[][] trips, int capacity) {
		int[] deltas = new int[1001];
		for (int[] trip : trips) {
			deltas[trip[1]] += trip[0];
			deltas[trip[2]] -= trip[0];
		}

		int passengerNum = 0;
		for (int i = 0; i < deltas.length; i++) {
			passengerNum += deltas[i];

			if (passengerNum > capacity) {
				return false;
			}
		}
		return true;
	}
}
