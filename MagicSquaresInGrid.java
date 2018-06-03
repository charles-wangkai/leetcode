import java.util.Arrays;
import java.util.stream.IntStream;

public class MagicSquaresInGrid {
	public int numMagicSquaresInside(int[][] grid) {
		return IntStream.range(0, grid.length - 2)
				.map(r -> (int) IntStream.range(0, grid[0].length - 2).filter(c -> isMagicSquare(grid, r, c)).count())
				.sum();
	}

	boolean isMagicSquare(int[][] grid, int r, int c) {
		int[] numbers = { grid[r][c], grid[r][c + 1], grid[r][c + 2], grid[r + 1][c], grid[r + 1][c + 1],
				grid[r + 1][c + 2], grid[r + 2][c], grid[r + 2][c + 1], grid[r + 2][c + 2] };

		if (!(Arrays.stream(numbers).distinct().count() == 9 && Arrays.stream(numbers).min().getAsInt() == 1
				&& Arrays.stream(numbers).max().getAsInt() == 9)) {
			return false;
		}

		int sum = grid[r][c] + grid[r][c + 1] + grid[r][c + 2];

		return grid[r + 1][c] + grid[r + 1][c + 1] + grid[r + 1][c + 2] == sum
				&& grid[r + 2][c] + grid[r + 2][c + 1] + grid[r + 2][c + 2] == sum
				&& grid[r][c] + grid[r + 1][c] + grid[r + 2][c] == sum
				&& grid[r][c + 1] + grid[r + 1][c + 1] + grid[r + 2][c + 1] == sum
				&& grid[r][c + 2] + grid[r + 1][c + 2] + grid[r + 2][c + 2] == sum
				&& grid[r][c] + grid[r + 1][c + 1] + grid[r + 2][c + 2] == sum
				&& grid[r][c + 2] + grid[r + 1][c + 1] + grid[r + 2][c] == sum;
	}
}
