public class Solution {
	public int hammingDistance(int x, int y) {
		int distance = 0;
		while (x != y) {
			if ((x & 1) != (y & 1)) {
				distance++;
			}

			x >>= 1;
			y >>= 1;
		}
		return distance;
	}
}
