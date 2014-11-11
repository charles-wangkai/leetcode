import java.util.ArrayList;
import java.util.List;

public class NQueens {
	public List<String[]> solveNQueens(int n) {
		List<String[]> solutions = new ArrayList<String[]>();
		search(solutions, new boolean[n], new boolean[2 * n - 1],
				new boolean[2 * n - 1], new int[n], 0);
		return solutions;
	}

	void search(List<String[]> solutions, boolean[] columns, boolean[] diffs,
			boolean[] sums, int[] rows, int index) {
		int size = rows.length;
		if (index == size) {
			String[] solution = new String[size];
			for (int i = 0; i < solution.length; i++) {
				solution[i] = "";
				for (int j = 0; j < rows[i]; j++) {
					solution[i] += ".";
				}
				solution[i] += "Q";
				for (int j = rows[i] + 1; j < size; j++) {
					solution[i] += ".";
				}
			}
			solutions.add(solution);
			return;
		}
		for (int i = 0; i < size; i++) {
			if (!columns[i] && !diffs[index - i + (size - 1)]
					&& !sums[index + i]) {
				rows[index] = i;
				columns[i] = true;
				diffs[index - i + (size - 1)] = true;
				sums[index + i] = true;
				search(solutions, columns, diffs, sums, rows, index + 1);
				columns[i] = false;
				diffs[index - i + (size - 1)] = false;
				sums[index + i] = false;
			}
		}
	}
}
