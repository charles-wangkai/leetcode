import java.util.HashMap;
import java.util.Map;

public class P_4Sum_II {
	public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
		Map<Integer, Integer> sum2count1 = buildSum2count(A, B);
		Map<Integer, Integer> sum2count2 = buildSum2count(C, D);

		int result = 0;
		for (int sum1 : sum2count1.keySet()) {
			if (sum2count2.containsKey(-sum1)) {
				result += sum2count1.get(sum1) * sum2count2.get(-sum1);
			}
		}
		return result;
	}

	Map<Integer, Integer> buildSum2count(int[] x, int[] y) {
		Map<Integer, Integer> sum2count = new HashMap<Integer, Integer>();
		for (int oneX : x) {
			for (int oneY : y) {
				int sum = oneX + oneY;

				if (!sum2count.containsKey(sum)) {
					sum2count.put(sum, 0);
				}
				sum2count.put(sum, sum2count.get(sum) + 1);
			}
		}
		return sum2count;
	}
}
