import java.util.Arrays;

public class Solution {
	public int findRotateSteps(String ring, String key) {
		int[][] steps = new int[key.length() + 1][ring.length()];
		for (int i = 0; i < steps.length; i++) {
			Arrays.fill(steps[i], -1);
		}

		steps[0][0] = 0;

		for (int i = 1; i <= key.length(); i++) {
			for (int j = 0; j < ring.length(); j++) {
				if (ring.charAt(j) == key.charAt(i - 1)) {
					for (int k = 0; k < ring.length(); k++) {
						if (steps[i - 1][k] >= 0) {
							steps[i][j] = Math.min(steps[i][j] < 0 ? Integer.MAX_VALUE : steps[i][j],
									steps[i - 1][k] + Math.min(Math.abs(j - k), ring.length() - Math.abs(j - k)) + 1);
						}
					}
				}
			}
		}

		return Arrays.stream(steps[key.length()]).filter(step -> step >= 0).min().getAsInt();
	}
}
