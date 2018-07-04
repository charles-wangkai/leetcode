import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int maximumGap(int[] num) {
		if (num.length < 2) {
			return 0;
		}
		sort(num);
		int maxGap = 0;
		for (int i = 0; i < num.length - 1; i++) {
			maxGap = Math.max(maxGap, num[i + 1] - num[i]);
		}
		return maxGap;
	}

	void sort(int[] num) {
		int mask = 1;
		for (int i = 0; i < 32; i++) {
			List<Integer> numbersWithZero = new ArrayList<Integer>();
			List<Integer> numbersWithOne = new ArrayList<Integer>();
			for (int number : num) {
				if ((number & mask) == 0) {
					numbersWithZero.add(number);
				} else {
					numbersWithOne.add(number);
				}
			}

			int index = 0;
			for (int number : numbersWithZero) {
				num[index] = number;
				index++;
			}
			for (int number : numbersWithOne) {
				num[index] = number;
				index++;
			}

			mask <<= 1;
		}
	}
}
