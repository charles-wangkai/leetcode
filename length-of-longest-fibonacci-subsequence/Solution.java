import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
	public int lenLongestFibSubseq(int[] A) {
		Set<Integer> numbers = Arrays.stream(A).boxed().collect(Collectors.toSet());

		int maxLength = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = i + 1; j < A.length; j++) {
				int length = findLength(numbers, A[i], A[j]);
				maxLength = Math.max(maxLength, length);
			}
		}
		return maxLength;
	}

	int findLength(Set<Integer> numbers, int prev, int current) {
		int length = 2;
		while (true) {
			int next = prev + current;

			if (!numbers.contains(next)) {
				break;
			}

			length++;
			prev = current;
			current = next;
		}
		return length >= 3 ? length : 0;
	}
}
