import java.util.SortedMap;
import java.util.TreeMap;

public class Solution {
	public int maxSumSubmatrix(int[][] matrix, int k) {
		int row = matrix.length;
		int col = matrix[0].length;

		int result = Integer.MIN_VALUE;
		for (int beginC = 0; beginC < col; beginC++) {
			int[] column = new int[row];
			for (int endC = beginC; endC < col; endC++) {
				for (int r = 0; r < row; r++) {
					column[r] += matrix[r][endC];
				}

				result = Math.max(result, findMaxSum(column, k));
			}
		}
		return result;
	}

	private int findMaxSum(int[] a, int k) {
		int[] sums = new int[a.length + 1];
		sums[0] = 0;
		for (int i = 1; i < sums.length; i++) {
			sums[i] = sums[i - 1] + a[i - 1];
		}

		SortedMap<Integer, Integer> sum2count = new TreeMap<Integer, Integer>();
		for (int sum : sums) {
			if (!sum2count.containsKey(sum)) {
				sum2count.put(sum, 0);
			}
			sum2count.put(sum, sum2count.get(sum) + 1);
		}

		int result = Integer.MIN_VALUE;
		for (int sum : sums) {
			sum2count.put(sum, sum2count.get(sum) - 1);
			if (sum2count.get(sum) == 0) {
				sum2count.remove(sum);
			}

			int target = sum + k;

			int chosen;
			if (sum2count.containsKey(target)) {
				chosen = target;
			} else {
				SortedMap<Integer, Integer> headMap = sum2count.headMap(target);
				if (headMap.isEmpty()) {
					continue;
				}
				chosen = headMap.lastKey();
			}

			result = Math.max(result, chosen - sum);
		}
		return result;
	}
}
