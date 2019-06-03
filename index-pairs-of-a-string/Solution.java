import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
	public int[][] indexPairs(String text, String[] words) {
		Set<String> wordSet = Arrays.stream(words).collect(Collectors.toSet());

		List<int[]> pairs = new ArrayList<>();
		for (int i = 0; i < text.length(); i++) {
			for (int j = i; j < text.length(); j++) {
				if (wordSet.contains(text.substring(i, j + 1))) {
					pairs.add(new int[] { i, j });
				}
			}
		}

		return pairs.toArray(new int[0][]);
	}
}
