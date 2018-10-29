import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Solution {
	public int[] beautifulArray(int N) {
		int[] A = IntStream.rangeClosed(1, N).toArray();
		adjust(A, 0, A.length - 1);
		return A;
	}

	void adjust(int[] A, int beginIndex, int endIndex) {
		if (beginIndex == endIndex) {
			return;
		}

		List<Integer> odds = new ArrayList<>();
		List<Integer> evens = new ArrayList<>();
		for (int i = beginIndex; i <= endIndex; i++) {
			if (i % 2 == 0) {
				evens.add(A[i]);
			} else {
				odds.add(A[i]);
			}
		}

		int index = beginIndex;
		for (int odd : odds) {
			A[index] = odd;
			index++;
		}
		for (int even : evens) {
			A[index] = even;
			index++;
		}

		adjust(A, beginIndex, beginIndex + odds.size() - 1);
		adjust(A, beginIndex + odds.size(), endIndex);
	}
}
