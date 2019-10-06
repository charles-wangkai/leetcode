import java.util.HashMap;
import java.util.Map;

public class Solution {
	public int longestSubsequence(int[] arr, int difference) {
		Map<Integer, Integer> lastNumberToLength = new HashMap<>();
		for (int element : arr) {
			lastNumberToLength.put(element, lastNumberToLength.getOrDefault(element - difference, 0) + 1);
		}

		return lastNumberToLength.values().stream().mapToInt(x -> x).max().getAsInt();
	}
}
