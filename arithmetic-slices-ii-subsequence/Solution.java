import java.util.HashMap;
import java.util.Map;

public class Solution {
	public int numberOfArithmeticSlices(int[] A) {
		@SuppressWarnings("unchecked")
		Map<Integer, Integer>[] delta2countList = new Map[A.length];

		int result = 0;
		for (int i = 0; i < A.length; i++) {
			delta2countList[i] = new HashMap<Integer, Integer>();

			for (int j = 0; j < i; j++) {
				long d = (long) A[i] - A[j];

				if (d < Integer.MIN_VALUE || d > Integer.MAX_VALUE) {
					continue;
				}

				int delta = (int) d;

				if (!delta2countList[i].containsKey(delta)) {
					delta2countList[i].put(delta, 0);
				}

				if (delta2countList[j].containsKey(delta)) {
					int prevCount = delta2countList[j].get(delta);

					delta2countList[i].put(delta, delta2countList[i].get(delta) + prevCount);
					result += prevCount;
				}

				delta2countList[i].put(delta, delta2countList[i].get(delta) + 1);
			}
		}

		return result;
	}
}
