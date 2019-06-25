import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
	public double[] sampleStats(int[] count) {
		return new double[] { computeMin(count), computeMax(count), computeMean(count), computeMedian(count),
				computeMode(count) };
	}

	double computeMin(int[] count) {
		for (int i = 0;; i++) {
			if (count[i] != 0) {
				return i;
			}
		}
	}

	double computeMax(int[] count) {
		for (int i = count.length - 1;; i--) {
			if (count[i] != 0) {
				return i;
			}
		}
	}

	double computeMean(int[] count) {
		return (double) IntStream.range(0, count.length).mapToLong(i -> (long) i * count[i]).sum()
				/ Arrays.stream(count).sum();
	}

	double computeMedian(int[] count) {
		int totalCount = Arrays.stream(count).sum();
		if (totalCount % 2 == 0) {
			return (findAt(count, totalCount / 2) + findAt(count, totalCount / 2 + 1)) / 2.0;
		} else {
			return findAt(count, (totalCount + 1) / 2);
		}
	}

	double findAt(int[] count, int k) {
		int sum = 0;
		for (int i = 0;; i++) {
			sum += count[i];

			if (sum >= k) {
				return i;
			}
		}
	}

	double computeMode(int[] count) {
		int maxCount = Arrays.stream(count).max().getAsInt();

		return IntStream.range(0, count.length).filter(i -> count[i] == maxCount).findAny().getAsInt();
	}
}
