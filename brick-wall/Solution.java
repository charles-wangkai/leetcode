import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	public int leastBricks(List<List<Integer>> wall) {
		Map<Integer, Integer> length2count = new HashMap<Integer, Integer>();
		for (List<Integer> row : wall) {
			int length = 0;
			for (int i = 0; i < row.size() - 1; i++) {
				length += row.get(i);

				if (!length2count.containsKey(length)) {
					length2count.put(length, 0);
				}
				length2count.put(length, length2count.get(length) + 1);
			}
		}

		return wall.size()
				- (length2count.isEmpty() ? 0 : length2count.values().stream().mapToInt(x -> x).max().getAsInt());
	}
}
