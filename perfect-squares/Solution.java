import java.util.HashMap;
import java.util.Map;

public class Solution {
	Map<Integer, Integer> cache = new HashMap<>();

	public int numSquares(int n) {
		return search(n, computeResultLimit(n));
	}

	int computeResultLimit(int n) {
		int resultLimit = 0;
		while (n != 0) {
			int root = (int) Math.round(Math.sqrt(n));
			if (root * root > n) {
				--root;
			}
			n -= root * root;
			++resultLimit;
		}

		return resultLimit;
	}

	Integer search(int n, int resultLimit) {
		if (n == 0) {
			return 0;
		}
		if (resultLimit == 0) {
			return null;
		}

		if (cache.containsKey(n)) {
			int result = cache.get(n);

			return (resultLimit >= result) ? result : null;
		}

		Integer result = null;
		for (int i = 1; i * i <= n; ++i) {
			Integer subResult = search(n - i * i, resultLimit - 1);

			if (subResult != null && (result == null || subResult + 1 < result)) {
				result = subResult + 1;
				resultLimit = subResult;
			}
		}

		if (result != null) {
			cache.put(n, result);
		}

		return result;
	}
}
