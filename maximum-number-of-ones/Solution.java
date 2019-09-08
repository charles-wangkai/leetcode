import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
	public int maximumNumberOfOnes(int width, int height, int sideLength, int maxOnes) {
		List<Integer> counts = new ArrayList<>();
		for (int r = 0; r < sideLength; r++) {
			for (int c = 0; c < sideLength; c++) {
				counts.add(((height - r - 1) / sideLength + 1) * ((width - c - 1) / sideLength + 1));
			}
		}

		return counts.stream().sorted(Collections.reverseOrder()).limit(maxOnes).mapToInt(x -> x).sum();
	}
}
