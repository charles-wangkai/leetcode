public class Solution {
	public int[] constructArray(int n, int k) {
		int[] result = new int[n];
		int index = 0;
		for (int i = 1; i <= n - k; i++) {
			result[index] = i;
			index++;
		}

		int number = n - k;
		int sign = 1;
		for (int step = k; step >= 1; step--) {
			number += step * sign;
			result[index] = number;
			index++;

			sign *= -1;
		}
		return result;
	}
}
