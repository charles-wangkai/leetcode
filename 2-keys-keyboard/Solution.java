import java.util.Arrays;

public class Solution {
	public int minSteps(int n) {
		int[] steps = new int[n + 1];
		Arrays.fill(steps, Integer.MAX_VALUE);
		steps[1] = 0;
		for (int i = 1; i < steps.length; i++) {
			int step = steps[i] + 1;
			for (int j = i + i; j < steps.length; j += i) {
				step++;
				steps[j] = Math.min(steps[j], step);
			}
		}
		return steps[n];
	}
}
