public class Solution {
	public double minmaxGasDist(int[] stations, int K) {
		double lower = 0;
		double upper = 100000000;
		while (upper - lower > 0.000001) {
			double middle = (lower + upper) / 2;

			int count = 0;
			for (int i = 0; i < stations.length - 1; i++) {
				count += Math.max(0, Math.ceil((stations[i + 1] - stations[i]) / middle) - 1);
			}

			if (count <= K) {
				upper = middle;
			} else {
				lower = middle;
			}
		}
		return (lower + upper) / 2;
	}
}
