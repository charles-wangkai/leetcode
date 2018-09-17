import java.util.HashMap;
import java.util.Map;

public class Solution {
	public int totalFruit(int[] tree) {
		Map<Integer, Integer> typeToCount = new HashMap<>();
		int maxLength = 0;
		int leftIndex = 0;
		for (int rightIndex = 0; rightIndex < tree.length; rightIndex++) {
			int type = tree[rightIndex];

			if (typeToCount.size() == 2 && !typeToCount.containsKey(type)) {
				while (typeToCount.size() == 2) {
					int leftType = tree[leftIndex];
					leftIndex++;

					typeToCount.put(leftType, typeToCount.get(leftType) - 1);
					if (typeToCount.get(leftType) == 0) {
						typeToCount.remove(leftType);
					}
				}
			}

			typeToCount.put(type, typeToCount.getOrDefault(type, 0) + 1);

			maxLength = Math.max(maxLength, rightIndex - leftIndex + 1);
		}
		return maxLength;
	}
}
