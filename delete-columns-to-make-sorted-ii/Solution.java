import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
	public int minDeletionSize(String[] A) {
		int deletionNum = 0;
		int length = 0;
		while (length != A[0].length()) {
			if (isSorted(truncate(A, length + 1))) {
				length++;
			} else {
				A = delete(A, length);
				deletionNum++;
			}
		}
		return deletionNum;
	}

	boolean isSorted(String[] truncated) {
		return IntStream.range(0, truncated.length - 1).allMatch(i -> truncated[i].compareTo(truncated[i + 1]) <= 0);
	}

	String[] truncate(String[] A, int length) {
		return Arrays.stream(A).map(s -> s.substring(0, length)).toArray(String[]::new);
	}

	String[] delete(String[] A, int index) {
		return Arrays.stream(A).map(s -> s.substring(0, index) + s.substring(index + 1)).toArray(String[]::new);
	}
}
