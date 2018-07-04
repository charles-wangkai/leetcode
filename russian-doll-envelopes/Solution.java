import java.util.Arrays;

public class Solution {
	public int maxEnvelopes(int[][] envelopes) {
		Arrays.sort(envelopes, (e1, e2) -> e1[0] - e2[0]);

		int[] maxLengths = new int[envelopes.length];
		int result = 0;
		for (int i = 0; i < maxLengths.length; i++) {
			maxLengths[i] = 1;
			for (int j = 0; j < i; j++) {
				if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
					maxLengths[i] = Math.max(maxLengths[i], maxLengths[j] + 1);
				}
			}
			result = Math.max(result, maxLengths[i]);
		}
		return result;
	}
}
