import java.util.Arrays;

public class P_3SumClosest {
	public int threeSumClosest(int[] num, int target) {
		Arrays.sort(num);
		Integer closest = null;
		for (int i = 0; i < num.length; i++) {
			int remain = target - num[i];
			for (int j = i + 1, k = num.length - 1; j < k;) {
				int sum = num[i] + num[j] + num[k];
				if (closest == null
						|| Math.abs(sum - target) < Math.abs(closest - target)) {
					closest = sum;
				}
				if (num[j] + num[k] <= remain) {
					j++;
				} else {
					k--;
				}
			}
		}
		return closest;
	}
}
