import java.util.HashMap;
import java.util.Map;

public class Solution {
	Map<Integer, Integer> number2result = new HashMap<Integer, Integer>();

	public int numSquares(int n) {
		return search(n, computeResultLimit(n));
	}

	int computeResultLimit(int number) {
		int resultLimit = 0;
		while (number != 0) {
			int root = (int) Math.round(Math.sqrt(number));
			if ((long) root * root > number) {
				root--;
			}
			number -= root * root;
			resultLimit++;
		}
		return resultLimit;
	}

	Integer search(int number, int resultLimit) {
		if (number == 0) {
			return 0;
		}
		if (resultLimit == 0) {
			return null;
		}

		if (number2result.containsKey(number)) {
			int result = number2result.get(number);
			return (resultLimit >= result) ? result : null;
		}

		Integer result = null;
		for (int i = 1; (long) i * i <= number; i++) {
			Integer subResult = search(number - i * i, resultLimit - 1);

			if (subResult != null) {
				result = Math.min(result == null ? Integer.MAX_VALUE : result,
						subResult + 1);
				resultLimit = Math.min(resultLimit, result - 1);
			}
		}

		if (result != null) {
			number2result.put(number, result);
		}
		return result;
	}
}
