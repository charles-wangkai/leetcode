import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
	public List<List<Integer>> shiftGrid(int[][] grid, int k) {
		int row = grid.length;
		int col = grid[0].length;
		int[][] result = new int[row][col];
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				int index = (r * col + c + k) % (row * col);

				result[index / col][index % col] = grid[r][c];
			}
		}

		return Arrays.stream(result).map(line -> Arrays.stream(line).boxed().collect(Collectors.toList()))
				.collect(Collectors.toList());
	}
}
