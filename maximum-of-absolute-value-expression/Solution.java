import java.util.Arrays;

public class Solution {
	public int maxAbsValExpr(int[] arr1, int[] arr2) {
		Metric[] metrics = { new Metric() {
			@Override
			int f(int value1, int value2, int index) {
				return value1 + value2 + index;
			}
		}, new Metric() {
			@Override
			int f(int value1, int value2, int index) {
				return value1 - value2 + index;
			}
		}, new Metric() {
			@Override
			int f(int value1, int value2, int index) {
				return -value1 + value2 + index;
			}
		}, new Metric() {
			@Override
			int f(int value1, int value2, int index) {
				return -value1 - value2 + index;
			}
		} };

		for (int i = 0; i < arr1.length; i++) {
			for (Metric metric : metrics) {
				metric.update(arr1[i], arr2[i], i);
			}
		}

		return Arrays.stream(metrics).mapToInt(metric -> metric.max - metric.min).max().getAsInt();
	}
}

abstract class Metric {
	int min = Integer.MAX_VALUE;
	int max = Integer.MIN_VALUE;

	abstract int f(int value1, int value2, int index);

	void update(int value1, int value2, int index) {
		int current = f(value1, value2, index);

		min = Math.min(min, current);
		max = Math.max(max, current);
	}
}