import java.util.Arrays;
import java.util.Random;

public class Solution {
	private Random random = new Random();
	private int[] limits;
	private int total;

	public Solution(int[] w) {
		int limit = 0;
		limits = new int[w.length];
		for (int i = 0; i < limits.length; i++) {
			limit += w[i];
			limits[i] = limit;
		}

		total = limit;
	}

	public int pickIndex() {
		int number = random.nextInt(total) + 1;

		int index = Arrays.binarySearch(limits, number);
		if (index < 0) {
			index = -1 - index;
		}

		return index;
	}
}

// Your Solution object will be instantiated and called as such:
// Solution obj = new Solution(w);
// int param_1 = obj.pickIndex();
