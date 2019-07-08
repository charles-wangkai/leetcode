public class Solution {
	public int[] corpFlightBookings(int[][] bookings, int n) {
		int[] deltas = new int[n + 1];
		for (int[] booking : bookings) {
			deltas[booking[0] - 1] += booking[2];
			deltas[booking[1]] -= booking[2];
		}

		int[] result = new int[n];
		int bookNum = 0;
		for (int i = 0; i < result.length; i++) {
			bookNum += deltas[i];

			result[i] = bookNum;
		}
		return result;
	}
}
