import java.util.ArrayList;
import java.util.List;

public class Solution {
	public List<String> findMissingRanges(int[] A, int lower, int upper) {
		List<String> missingRanges = new ArrayList<String>();
		int begin = lower;
		for (int number : A) {
			if (number != begin) {
				missingRanges.add(generateRange(begin, number - 1));
			}
			begin = number + 1;
		}
		if (begin <= upper) {
			missingRanges.add(generateRange(begin, upper));
		}
		return missingRanges;
	}

	String generateRange(int begin, int end) {
		return (begin == end) ? (begin + "") : (begin + "->" + end);
	}
}
