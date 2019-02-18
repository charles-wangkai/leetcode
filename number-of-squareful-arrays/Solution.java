import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	int solutionNum;

	public int numSquarefulPerms(int[] A) {
		Map<Integer, Integer> valueToCount = new HashMap<>();
		Arrays.stream(A).forEach(value -> increase(valueToCount, value));

		Map<Integer, List<Integer>> valueToAdjacents = new HashMap<>();
		for (int value : valueToCount.keySet()) {
			List<Integer> adjacents = new ArrayList<>();
			for (int adjacent : valueToCount.keySet()) {
				if (isSquare(value + adjacent)) {
					adjacents.add(adjacent);
				}
			}

			valueToAdjacents.put(value, adjacents);
		}

		solutionNum = 0;
		for (int value : valueToCount.keySet().toArray(new Integer[0])) {
			search(valueToAdjacents, valueToCount, value);
		}
		return solutionNum;
	}

	void search(Map<Integer, List<Integer>> valueToAdjacents, Map<Integer, Integer> valueToCount, int value) {
		decrease(valueToCount, value);

		if (valueToCount.isEmpty()) {
			solutionNum++;
		} else {
			for (int adjacent : valueToAdjacents.get(value)) {
				if (valueToCount.containsKey(adjacent)) {
					search(valueToAdjacents, valueToCount, adjacent);
				}
			}
		}

		increase(valueToCount, value);
	}

	void increase(Map<Integer, Integer> valueToCount, int value) {
		valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
	}

	void decrease(Map<Integer, Integer> valueToCount, int value) {
		valueToCount.put(value, valueToCount.get(value) - 1);

		if (valueToCount.get(value) == 0) {
			valueToCount.remove(value);
		}
	}

	boolean isSquare(int n) {
		int root = (int) Math.round(Math.sqrt(n));
		return root * root == n;
	}
}
