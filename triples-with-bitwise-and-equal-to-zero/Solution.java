import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
	public int countTriplets(int[] A) {
		Map<Integer, Integer> cache = new HashMap<>();
		int result = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length; j++) {
				int intermediate = A[i] & A[j];

				int ways;
				if (cache.containsKey(intermediate)) {
					ways = cache.get(intermediate);
				} else {
					ways = computeWays(A, intermediate);
					cache.put(intermediate, ways);
				}
				result += ways;
			}
		}
		return result;
	}

	int computeWays(int[] A, int intermediate) {
		return (int) Arrays.stream(A).filter(x -> (x & intermediate) == 0).count();
	}
}
