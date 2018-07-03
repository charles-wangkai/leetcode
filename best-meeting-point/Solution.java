import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
	public int minTotalDistance(int[][] grid) {
		List<Integer> homeXs = new ArrayList<Integer>();
		List<Integer> homeYs = new ArrayList<Integer>();

		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid[x].length; y++) {
				if (grid[x][y] == 1) {
					homeXs.add(x);
					homeYs.add(y);
				}
			}
		}

		return findMinTotalDistance(homeXs) + findMinTotalDistance(homeYs);
	}

	int findMinTotalDistance(List<Integer> coordinates) {
		Collections.sort(coordinates);

		int minTotalDistance = 0;
		for (int i = 0, j = coordinates.size() - 1; i < j; i++, j--) {
			minTotalDistance += coordinates.get(j) - coordinates.get(i);
		}
		return minTotalDistance;
	}
}
