import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	public List<List<Integer>> largeGroupPositions(String S) {
		List<List<Integer>> result = new ArrayList<>();

		int beginIndex = 0;
		char currentLetter = 0;
		for (int i = 0; i <= S.length(); i++) {
			if (i == S.length() || S.charAt(i) != currentLetter) {
				if (i - beginIndex >= 3) {
					result.add(Arrays.asList(beginIndex, i - 1));
				}

				beginIndex = i;
				if (i < S.length()) {
					currentLetter = S.charAt(i);
				}
			}
		}

		return result;
	}
}
