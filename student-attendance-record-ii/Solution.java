public class Solution {
	static final int MODULUS = 1000000007;

	public int checkRecord(int n) {
		int[][][] records = new int[n + 1][2][3];
		records[0][0][0] = 1;

		for (int i = 1; i <= n; i++) {
			records[i][0][0] = addMod(addMod(records[i - 1][0][0], records[i - 1][0][1]), records[i - 1][0][2]);
			records[i][0][1] = records[i - 1][0][0];
			records[i][0][2] = records[i - 1][0][1];

			records[i][1][0] = addMod(addMod(addMod(records[i - 1][0][0], records[i - 1][0][1]), records[i - 1][0][2]),
					addMod(addMod(records[i - 1][1][0], records[i - 1][1][1]), records[i - 1][1][2]));
			records[i][1][1] = records[i - 1][1][0];
			records[i][1][2] = records[i - 1][1][1];
		}

		int result = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				result = addMod(result, records[n][i][j]);
			}
		}
		return result;
	}

	int addMod(int x, int y) {
		return (x + y) % MODULUS;
	}
}
