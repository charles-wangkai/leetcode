public class Solution {
	public int largestOverlap(int[][] A, int[][] B) {
		int result = 0;
		int size = A.length;
		for (int rOffset = -(size - 1); rOffset <= size - 1; rOffset++) {
			for (int cOffset = -(size - 1); cOffset <= size - 1; cOffset++) {
				result = Math.max(result, overlap(translate(A, rOffset, cOffset), B));
			}
		}
		return result;
	}

	int[][] translate(int[][] image, int rOffset, int cOffset) {
		int size = image.length;
		int[][] result = new int[size][size];
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				int originalR = r + rOffset;
				int originalC = c + cOffset;

				if (originalR >= 0 && originalR < size && originalC >= 0 && originalC < size) {
					result[r][c] = image[originalR][originalC];
				}
			}
		}
		return result;
	}

	int overlap(int[][] image1, int[][] image2) {
		int size = image1.length;
		int result = 0;
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				if (image1[r][c] == 1 && image2[r][c] == 1) {
					result++;
				}
			}
		}
		return result;
	}
}
