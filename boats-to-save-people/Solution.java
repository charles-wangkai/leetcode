import java.util.NavigableMap;
import java.util.TreeMap;

public class Solution {
	public int numRescueBoats(int[] people, int limit) {
		NavigableMap<Integer, Integer> weightToCount = new TreeMap<>();
		for (int weight : people) {
			weightToCount.put(weight, weightToCount.getOrDefault(weight, 0) + 1);
		}

		int boatNum = 0;
		while (!weightToCount.isEmpty()) {
			boatNum++;

			int maxWeight = weightToCount.lastKey();
			int maxWeightCount = weightToCount.get(maxWeight);
			decreaseCount(weightToCount, maxWeight);

			if (maxWeight * 2 <= limit && maxWeightCount >= 2) {
				decreaseCount(weightToCount, maxWeight);

				continue;
			}

			Integer otherWeight = weightToCount.floorKey(limit - maxWeight);
			if (otherWeight != null) {
				decreaseCount(weightToCount, otherWeight);
			}
		}
		return boatNum;
	}

	void decreaseCount(NavigableMap<Integer, Integer> weightToCount, int weight) {
		weightToCount.put(weight, weightToCount.get(weight) - 1);

		if (weightToCount.get(weight) == 0) {
			weightToCount.remove(weight);
		}
	}
}
