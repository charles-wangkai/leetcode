import java.util.Arrays;

public class ShortestDistanceToACharacter {
	public int[] shortestToChar(String S, char C) {
		int[] result = new int[S.length()];
		Arrays.fill(result, Integer.MAX_VALUE);

		int leftIndex = -1;
		for (int i = 0; i < S.length(); i++) {
			if (S.charAt(i) == C) {
				leftIndex = i;
			}

			if (leftIndex >= 0) {
				result[i] = Math.min(result[i], i - leftIndex);
			}
		}

		int rightIndex = S.length();
		for (int i = S.length() - 1; i >= 0; i--) {
			if (S.charAt(i) == C) {
				rightIndex = i;
			}

			if (rightIndex < S.length()) {
				result[i] = Math.min(result[i], rightIndex - i);
			}
		}

		return result;
	}
}
