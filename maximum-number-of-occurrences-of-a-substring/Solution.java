import java.util.HashMap;
import java.util.Map;

public class Solution {
	public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
		Map<String, Integer> strToCount = new HashMap<>();
		for (int size = minSize; size <= maxSize; size++) {
			int beginIndex = 0;
			Map<Character, Integer> letterToCount = new HashMap<>();
			for (int endIndex = 0; endIndex < s.length(); endIndex++) {
				updateMap(letterToCount, s.charAt(endIndex), 1);

				if (endIndex - beginIndex == size) {
					updateMap(letterToCount, s.charAt(beginIndex), -1);
					beginIndex++;
				}

				if (endIndex - beginIndex + 1 == size && letterToCount.size() <= maxLetters) {
					updateMap(strToCount, s.substring(beginIndex, endIndex + 1), 1);
				}
			}
		}

		return strToCount.values().stream().mapToInt(x -> x).max().orElse(0);
	}

	<T> void updateMap(Map<T, Integer> map, T key, int valueDelta) {
		map.put(key, map.getOrDefault(key, 0) + valueDelta);
		map.remove(key, 0);
	}
}
