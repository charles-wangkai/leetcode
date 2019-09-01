import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {
	public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
		Map<String, Integer> patternToCount = new HashMap<>();
		for (String word : words) {
			String pattern = buildPattern(word);

			patternToCount.put(pattern, patternToCount.getOrDefault(pattern, 0) + 1);
		}

		List<Integer> result = new ArrayList<>();
		for (String puzzle : puzzles) {
			int matchedCount = 0;
			for (int code = 0; code < 1 << (puzzle.length() - 1); code++) {
				StringBuilder sb = new StringBuilder();
				sb.append(puzzle.charAt(0));

				int p = code;
				for (int i = 1; i < puzzle.length(); i++) {
					if (p % 2 != 0) {
						sb.append(puzzle.charAt(i));
					}

					p /= 2;
				}

				matchedCount += patternToCount.getOrDefault(buildPattern(sb.toString()), 0);
			}

			result.add(matchedCount);
		}

		return result;
	}

	String buildPattern(String s) {
		return s.chars().distinct().sorted().mapToObj(ch -> String.valueOf((char) ch)).collect(Collectors.joining());
	}
}
