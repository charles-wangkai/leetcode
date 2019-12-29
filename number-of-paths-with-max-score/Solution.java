import java.util.List;

public class Solution {
	static final int MODULUS = 1_000_000_007;
	static final int[] R_OFFSETS = { 0, 1, 1 };
	static final int[] C_OFFSETS = { 1, 0, 1 };

	public int[] pathsWithMaxScore(List<String> board) {
		int size = board.size();

		Element[][] elements = new Element[size][size];
		for (int r = size - 1; r >= 0; --r) {
			for (int c = size - 1; c >= 0; --c) {
				char ch = board.get(r).charAt(c);

				int maxSum;
				int pathNum;
				if (ch == 'S') {
					maxSum = 0;
					pathNum = 1;
				} else {
					maxSum = -1;
					pathNum = 0;

					if (ch != 'X') {
						for (int i = 0; i < R_OFFSETS.length; ++i) {
							int adjR = r + R_OFFSETS[i];
							int adjC = c + C_OFFSETS[i];
							if (adjR < size && adjC < size) {
								maxSum = Math.max(maxSum, elements[adjR][adjC].maxSum);
							}
						}

						for (int i = 0; i < R_OFFSETS.length; ++i) {
							int adjR = r + R_OFFSETS[i];
							int adjC = c + C_OFFSETS[i];
							if (adjR < size && adjC < size && elements[adjR][adjC].maxSum == maxSum) {
								pathNum = addMod(pathNum, elements[adjR][adjC].pathNum);
							}
						}

						if (maxSum != -1) {
							maxSum += (ch >= '1' && ch <= '9') ? (ch - '0') : 0;
						}
					}
				}

				elements[r][c] = new Element(maxSum, pathNum);
			}
		}

		return new int[] { Math.max(0, elements[0][0].maxSum), elements[0][0].pathNum };
	}

	int addMod(int x, int y) {
		return (x + y) % MODULUS;
	}
}

class Element {
	int maxSum;
	int pathNum;

	Element(int maxSum, int pathNum) {
		this.maxSum = maxSum;
		this.pathNum = pathNum;
	}
}