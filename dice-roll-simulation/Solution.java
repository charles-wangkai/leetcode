import java.util.Arrays;

public class Solution {
	static final int MODULUS = 1_000_000_007;

	public int dieSimulator(int n, int[] rollMax) {
		int[][] wayNums = new int[rollMax.length][];
		for (int i = 0; i < wayNums.length; i++) {
			wayNums[i] = new int[rollMax[i]];
			wayNums[i][0] = 1;
		}

		for (int step = 0; step < n - 1; step++) {
			int[][] nextWayNums = new int[wayNums.length][];
			for (int i = 0; i < nextWayNums.length; i++) {
				nextWayNums[i] = new int[wayNums[i].length];
			}

			for (int i = 0; i < nextWayNums.length; i++) {
				for (int p = 0; p < wayNums.length; p++) {
					if (p != i) {
						for (int q = 0; q < wayNums[p].length; q++) {
							nextWayNums[i][0] = addMod(nextWayNums[i][0], wayNums[p][q]);
						}
					}
				}

				for (int j = 1; j < nextWayNums[i].length; j++) {
					nextWayNums[i][j] = addMod(nextWayNums[i][j], wayNums[i][j - 1]);
				}
			}

			wayNums = nextWayNums;
		}

		return Arrays.stream(wayNums).mapToInt(row -> Arrays.stream(row).reduce(0, this::addMod)).reduce(0,
				this::addMod);
	}

	int addMod(int x, int y) {
		return (x + y) % MODULUS;
	}
}
