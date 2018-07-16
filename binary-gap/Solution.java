public class Solution {
	public int binaryGap(int N) {
		int maxDistance = 0;
		int distance = -1;
		while (N != 0) {
			if (distance >= 0) {
				distance++;
			}

			if (N % 2 == 1) {
				maxDistance = Math.max(maxDistance, distance);
				distance = 0;
			}

			N /= 2;
		}
		return maxDistance;
	}
}
