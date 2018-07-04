public class Solution {
	public int totalNQueens(int n) {
		return search(n, new boolean[n], new boolean[2 * n - 1],
				new boolean[2 * n - 1], 0);
	}

	int search(int n, boolean[] columns, boolean[] diffs, boolean[] sums,
			int index) {
		if (index == n) {
			return 1;
		}
		int total = 0;
		for (int i = 0; i < n; i++) {
			if (!columns[i] && !diffs[index - i + (n - 1)] && !sums[index + i]) {
				columns[i] = true;
				diffs[index - i + (n - 1)] = true;
				sums[index + i] = true;
				total += search(n, columns, diffs, sums, index + 1);
				columns[i] = false;
				diffs[index - i + (n - 1)] = false;
				sums[index + i] = false;
			}
		}
		return total;
	}
}
