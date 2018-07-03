import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

public class Solution {
	public int deleteAndEarn(int[] nums) {
		SortedMap<Integer, Integer> num2count = new TreeMap<Integer, Integer>();
		for (int num : nums) {
			num2count.put(num, num2count.getOrDefault(num, 0) + 1);
		}

		int maxPoint = 0;
		int prevPrevPoint = 0;
		int prevPoint = 0;
		int prevNum = 0;
		for (Entry<Integer, Integer> entry : num2count.entrySet()) {
			int num = entry.getKey();
			int count = entry.getValue();

			int currPoint = Math.max(num * count + (num == prevNum + 1 ? prevPrevPoint : prevPoint), prevPoint);
			maxPoint = Math.max(maxPoint, currPoint);

			prevPrevPoint = prevPoint;
			prevPoint = currPoint;
			prevNum = num;
		}
		return maxPoint;
	}
}
