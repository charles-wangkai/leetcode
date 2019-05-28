import java.util.stream.IntStream;

public class Solution {
	public int maxSatisfied(int[] customers, int[] grumpy, int X) {
		int satisfiedNum = IntStream.range(0, customers.length).map(i -> customers[i] * (1 - grumpy[i])).sum();
		for (int i = 0; i < X; i++) {
			if (grumpy[i] == 1) {
				satisfiedNum += customers[i];
			}
		}

		int maxSatisfiedNum = satisfiedNum;
		int beginIndex = 0;
		for (int endIndex = X; endIndex < customers.length; endIndex++) {
			if (grumpy[endIndex] == 1) {
				satisfiedNum += customers[endIndex];
			}

			if (grumpy[beginIndex] == 1) {
				satisfiedNum -= customers[beginIndex];
			}
			beginIndex++;

			maxSatisfiedNum = Math.max(maxSatisfiedNum, satisfiedNum);
		}
		return maxSatisfiedNum;
	}
}
