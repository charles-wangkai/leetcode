import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
	static final int[] IMPOSSIBLE_REUSLT = { -1, -1 };

	public int[] threeEqualParts(int[] A) {
		int oneNum = (int) Arrays.stream(A).filter(bit -> bit == 1).count();
		if (oneNum == 0) {
			return new int[] { 0, 2 };
		}
		if (oneNum % 3 != 0) {
			return IMPOSSIBLE_REUSLT;
		}

		int partOneNum = oneNum / 3;

		int rightBeginIndex = findRightBeginIndex(A, partOneNum);
		int partLength = A.length - rightBeginIndex;

		int leftBeginIndex = 0;
		while (A[leftBeginIndex] == 0) {
			leftBeginIndex++;
		}

		if (leftBeginIndex + partLength > rightBeginIndex) {
			return IMPOSSIBLE_REUSLT;
		}
		if (!isSameSequence(A, leftBeginIndex, rightBeginIndex, partLength)) {
			return IMPOSSIBLE_REUSLT;
		}

		int middleBeginIndex = leftBeginIndex + partLength;
		while (A[middleBeginIndex] == 0) {
			middleBeginIndex++;
		}

		if (middleBeginIndex + partLength > rightBeginIndex) {
			return IMPOSSIBLE_REUSLT;
		}
		if (!isSameSequence(A, middleBeginIndex, rightBeginIndex, partLength)) {
			return IMPOSSIBLE_REUSLT;
		}
		if (!isAllZeros(A, middleBeginIndex + partLength, rightBeginIndex)) {
			return IMPOSSIBLE_REUSLT;
		}

		return new int[] { leftBeginIndex + partLength - 1, middleBeginIndex + partLength };
	}

	boolean isSameSequence(int[] A, int beginIndex1, int beginIndex2, int partLength) {
		return IntStream.range(0, partLength).allMatch(i -> A[beginIndex1 + i] == A[beginIndex2 + i]);
	}

	boolean isAllZeros(int[] A, int beginIndex, int endIndex) {
		return IntStream.range(beginIndex, endIndex).allMatch(i -> A[i] == 0);
	}

	int findRightBeginIndex(int[] A, int partOneNum) {
		int oneNum = 0;
		for (int i = A.length - 1;; i--) {
			if (A[i] == 1) {
				oneNum++;

				if (oneNum == partOneNum) {
					return i;
				}
			}
		}
	}
}
