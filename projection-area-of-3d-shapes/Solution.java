import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
	public int projectionArea(int[][] grid) {
		return computeAreaXY(grid) + computeAreaYZ(grid) + computeAreaZX(grid);
	}

	int computeAreaXY(int[][] grid) {
		return Arrays.stream(grid).mapToInt(row -> (int) Arrays.stream(row).filter(cell -> cell != 0).count()).sum();
	}

	int computeAreaYZ(int[][] grid) {
		return Arrays.stream(grid).mapToInt(row -> Arrays.stream(row).max().getAsInt()).sum();
	}

	int computeAreaZX(int[][] grid) {
		return IntStream.range(0, grid.length)
				.map(c -> IntStream.range(0, grid.length).map(r -> grid[r][c]).max().getAsInt()).sum();
	}
}
