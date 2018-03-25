import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class SplitArrayWithSameAverage {
	public boolean splitArraySameAverage(int[] A) {
		if (A.length == 1) {
			return false;
		}

		for (int i = 0; i < A.length; i++) {
			A[i] *= A.length;
		}
		int average = Arrays.stream(A).sum() / A.length;
		for (int i = 0; i < A.length; i++) {
			A[i] -= average;
		}

		int leftEndIndex = (A.length + 1) / 2 - 1;
		int rightBeginIndex = leftEndIndex + 1;

		Set<Integer> leftSums = buildSums(A, 0, leftEndIndex);
		Set<Integer> rightSums = buildSums(A, rightBeginIndex, A.length - 1);

		if (leftSums.contains(0) || rightSums.contains(0)) {
			return true;
		}

		int leftTotal = IntStream.rangeClosed(0, leftEndIndex).map(i -> A[i]).sum();
		for (int leftSum : leftSums) {
			if (leftSum != leftTotal && rightSums.contains(-leftSum)) {
				return true;
			}
		}
		return false;
	}

	Set<Integer> buildSums(int[] A, int beginIndex, int endIndex) {
		Set<Integer> sums = new HashSet<Integer>();
		for (int i = beginIndex; i <= endIndex; i++) {
			Set<Integer> nextSums = new HashSet<Integer>(sums);
			nextSums.add(A[i]);
			for (int sum : sums) {
				nextSums.add(sum + A[i]);
			}

			sums = nextSums;
		}
		return sums;
	}
}
