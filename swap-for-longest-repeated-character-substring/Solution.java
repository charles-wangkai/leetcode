import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	public int maxRepOpt1(String text) {
		Map<Character, List<Range>> letterToRanges = new HashMap<>();
		char current = 0;
		int beginIndex = -1;
		for (int i = 0; i <= text.length(); i++) {
			if (i == text.length() || text.charAt(i) != current) {
				if (current != 0) {
					if (!letterToRanges.containsKey(current)) {
						letterToRanges.put(current, new ArrayList<>());
					}

					letterToRanges.get(current).add(new Range(beginIndex, i - 1));
				}

				if (i != text.length()) {
					current = text.charAt(i);
				}
				beginIndex = i;
			}
		}

		int result = 0;
		for (List<Range> ranges : letterToRanges.values()) {
			for (Range range : ranges) {
				result = Math.max(result, range.getLength() + ((ranges.size() == 1) ? 0 : 1));
			}

			for (int i = 0; i < ranges.size() - 1; i++) {
				if (ranges.get(i).endIndex + 2 == ranges.get(i + 1).beginIndex) {
					result = Math.max(result,
							ranges.get(i).getLength() + ranges.get(i + 1).getLength() + ((ranges.size() == 2) ? 0 : 1));
				}
			}
		}

		return result;
	}
}

class Range {
	int beginIndex;
	int endIndex;

	Range(int beginIndex, int endIndex) {
		this.beginIndex = beginIndex;
		this.endIndex = endIndex;
	}

	int getLength() {
		return endIndex - beginIndex + 1;
	}
}