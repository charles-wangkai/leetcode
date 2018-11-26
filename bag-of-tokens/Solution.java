import java.util.Arrays;

public class Solution {
	public int bagOfTokensScore(int[] tokens, int P) {
		Arrays.sort(tokens);

		int endIndex = -1;
		int sum = 0;
		while (endIndex + 1 < tokens.length && sum + tokens[endIndex + 1] <= P) {
			sum += tokens[endIndex + 1];
			endIndex++;
		}
		int maxPoint = endIndex + 1;

		int power = P;
		for (int i = 0, j = tokens.length - 1; i < j && power >= tokens[i]; i++, j--) {
			power += tokens[j] - tokens[i];

			sum -= tokens[i];
			if (endIndex == j) {
				sum -= tokens[endIndex];
				endIndex--;
			}
			while (endIndex + 1 < j && sum + tokens[endIndex + 1] <= power) {
				sum += tokens[endIndex + 1];
				endIndex++;
			}

			maxPoint = Math.max(maxPoint, endIndex - i);
		}

		return maxPoint;
	}
}
