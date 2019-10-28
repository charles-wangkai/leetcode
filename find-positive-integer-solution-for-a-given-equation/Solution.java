import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// This is the custom function interface.
// You should not implement it, or speculate about its implementation
interface CustomFunction {
	// Returns f(x, y) for any given positive integers x and y.
	// Note that f(x, y) is increasing with respect to both x and y.
	// i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
	public int f(int x, int y);
};

public class Solution {
	public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
		List<List<Integer>> result = new ArrayList<>();

		for (int x = 1;; x++) {
			if (customfunction.f(x, 1) > z) {
				break;
			}

			int lowerY = 1;
			int upperY = 1000;
			while (lowerY <= upperY) {
				int middleY = (lowerY + upperY) / 2;

				int f = customfunction.f(x, middleY);
				if (f < z) {
					lowerY = middleY + 1;
				} else if (f > z) {
					upperY = middleY - 1;
				} else {
					result.add(Arrays.asList(x, middleY));

					break;
				}
			}
		}

		return result;
	}
}
