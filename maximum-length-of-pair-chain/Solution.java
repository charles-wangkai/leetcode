import java.util.Arrays;

public class Solution {
	public int findLongestChain(int[][] pairs) {
		Arrays.sort(pairs, (p1, p2) -> p1[0] - p2[0]);

		int maxChain = 0;
		int[] chains = new int[pairs.length];
		Arrays.fill(chains, 1);
		for (int i = 0; i < chains.length; i++) {
			for (int j = 0; j < i; j++) {
				if (pairs[j][1] < pairs[i][0]) {
					chains[i] = Math.max(chains[i], chains[j] + 1);
				}
			}
			maxChain = Math.max(maxChain, chains[i]);
		}
		return maxChain;
	}
}
