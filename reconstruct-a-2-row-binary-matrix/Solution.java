import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
	public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
		List<List<Integer>> result = Arrays.asList(new ArrayList<>(), new ArrayList<>());
		int upperSum = 0;
		int lowerSum = 0;
		for (int sum : colsum) {
			if (sum == 0) {
				result.get(0).add(0);
				result.get(1).add(0);
			} else if (sum == 2) {
				result.get(0).add(1);
				result.get(1).add(1);

				upperSum++;
				lowerSum++;
			} else {
				result.get(0).add(null);
				result.get(1).add(null);
			}
		}

		for (int i = 0; i < result.get(0).size(); i++) {
			if (result.get(0).get(i) == null) {
				if (upperSum < upper) {
					result.get(0).set(i, 1);
					result.get(1).set(i, 0);

					upperSum++;
				} else {
					result.get(0).set(i, 0);
					result.get(1).set(i, 1);

					lowerSum++;
				}
			}
		}

		if (upperSum == upper && lowerSum == lower) {
			return result;
		} else {
			return Collections.emptyList();
		}
	}
}
