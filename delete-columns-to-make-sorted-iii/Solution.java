import java.util.Arrays;

public class Solution {
	public int minDeletionSize(String[] A) {
		int[] maxLengths = new int[A[0].length()];
		for (int i = 0; i < maxLengths.length; i++) {
			maxLengths[i] = 1;
			for (int j = 0; j < i; j++) {
				if (canConcat(A, j, i)) {
					maxLengths[i] = Math.max(maxLengths[i], maxLengths[j] + 1);
				}
			}
		}
		return maxLengths.length - Arrays.stream(maxLengths).max().getAsInt();
	}

	boolean canConcat(String[] A, int lowerIndex, int upperIndex) {
		return Arrays.stream(A).allMatch(s -> s.charAt(lowerIndex) <= s.charAt(upperIndex));
	}
}
