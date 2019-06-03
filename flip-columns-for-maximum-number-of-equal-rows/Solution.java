import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	public int maxEqualRowsAfterFlips(int[][] matrix) {
		Map<List<Integer>, Integer> patternToCount = new HashMap<>();
		for (int[] row : matrix) {
			List<Integer> pattern = new ArrayList<>();
			for (int i = 1; i < row.length; i++) {
				pattern.add(row[i] ^ row[0]);
			}

			patternToCount.put(pattern, patternToCount.getOrDefault(pattern, 0) + 1);
		}

		return patternToCount.values().stream().mapToInt(x -> x).max().getAsInt();
	}
}
