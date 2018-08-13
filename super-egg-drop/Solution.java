public class Solution {
	public int superEggDrop(int K, int N) {
		int[][] maxFloors = new int[N + 1][K + 1];
		for (int move = 1;; move++) {
			for (int k = 1; k <= K; k++) {
				maxFloors[move][k] = maxFloors[move - 1][k - 1] + maxFloors[move - 1][k] + 1;
			}

			if (maxFloors[move][K] >= N) {
				return move;
			}
		}
	}
}
