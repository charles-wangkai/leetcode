import java.util.stream.IntStream;

public class Solution {
	static final int SIZE = 3;

	public String tictactoe(int[][] moves) {
		char[][] grid = new char[SIZE][SIZE];
		for (int i = 0; i < moves.length; i++) {
			grid[moves[i][0]][moves[i][1]] = (i % 2 == 0) ? 'X' : 'O';
		}

		if (isWin(grid, 'X')) {
			return "A";
		} else if (isWin(grid, 'O')) {
			return "B";
		} else if (isFinished(grid)) {
			return "Draw";
		} else {
			return "Pending";
		}
	}

	boolean isWin(char[][] grid, char target) {
		return IntStream.range(0, SIZE).anyMatch(r -> IntStream.range(0, SIZE).allMatch(c -> grid[r][c] == target))
				|| IntStream.range(0, SIZE).anyMatch(c -> IntStream.range(0, SIZE).allMatch(r -> grid[r][c] == target))
				|| IntStream.range(0, SIZE).allMatch(r -> grid[r][r] == target)
				|| IntStream.range(0, SIZE).allMatch(r -> grid[r][SIZE - 1 - r] == target);
	}

	boolean isFinished(char[][] grid) {
		return IntStream.range(0, SIZE).allMatch(r -> IntStream.range(0, SIZE).allMatch(c -> grid[r][c] != 0));
	}
}
