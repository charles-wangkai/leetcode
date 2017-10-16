import java.util.HashMap;
import java.util.Map;

public class DegreeOfAnArray {
	public int findShortestSubArray(int[] nums) {
		Map<Integer, Element> num2element = new HashMap<Integer, Element>();
		for (int i = 0; i < nums.length; i++) {
			if (!num2element.containsKey(nums[i])) {
				num2element.put(nums[i], new Element(i));
			}

			Element element = num2element.get(nums[i]);
			element.count++;
			element.maxIndex = i;
		}

		int maxCount = num2element.values().stream().mapToInt(element -> element.count).max().getAsInt();
		return num2element.values().stream().filter(element -> element.count == maxCount)
				.mapToInt(element -> element.maxIndex - element.minIndex + 1).min().getAsInt();
	}
}

class Element {
	int count = 0;
	int minIndex;
	int maxIndex;

	Element(int minIndex) {
		this.minIndex = minIndex;
	}
}