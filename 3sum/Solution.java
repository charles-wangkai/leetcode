import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {
	public List<List<Integer>> threeSum(int[] num) {
		Map<Integer, Integer> number2count = new HashMap<Integer, Integer>();
		for (int oneNum : num) {
			increaseCount(number2count, oneNum);
		}

		Set<List<Integer>> solutionSet = new HashSet<List<Integer>>();
		for (int a : number2count.keySet()) {
			decreaseCount(number2count, a);
			for (int b : number2count.keySet()) {
				if (number2count.get(b) == 0) {
					continue;
				}
				decreaseCount(number2count, b);
				int c = -a - b;
				if (number2count.containsKey(c) && number2count.get(c) > 0) {
					List<Integer> solution = Arrays.asList(a, b, c);
					Collections.sort(solution);
					solutionSet.add(solution);
				}
				increaseCount(number2count, b);
			}
			increaseCount(number2count, a);
		}

		return new ArrayList<List<Integer>>(solutionSet);
	}

	void increaseCount(Map<Integer, Integer> number2count, int number) {
		if (!number2count.containsKey(number)) {
			number2count.put(number, 0);
		}
		number2count.put(number, number2count.get(number) + 1);
	}

	void decreaseCount(Map<Integer, Integer> number2count, int number) {
		number2count.put(number, number2count.get(number) - 1);
	}
}
