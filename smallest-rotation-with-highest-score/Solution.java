public class Solution {
	public int bestRotation(int[] A) {
		int N = A.length;
		int[] steps = new int[N];

		for (int i = 0; i < N; i++) {
			if (A[i] <= i) {
				if (A[i] >= 1) {
					decrease(steps, i - A[i] + 1);
					increase(steps, i + 1);
				}
			} else {
				increase(steps, i + 1);
				decrease(steps, i + N - 1 - A[i] + 2);
			}
		}

		int index = 0;
		int max = 0;
		int current = 0;
		for (int i = 0; i < N; i++) {
			current += steps[i];

			if (current > max) {
				index = i;
				max = current;
			}
		}
		return index;
	}

	void increase(int[] steps, int index) {
		if (index < steps.length) {
			steps[index]++;
		}
	}

	void decrease(int[] steps, int index) {
		if (index < steps.length) {
			steps[index]--;
		}
	}
}
