import java.util.Deque;
import java.util.LinkedList;

public class Solution {
	public int shortestSubarray(int[] A, int K) {
		int length = A.length;

		int[] sums = new int[length + 1];
		for (int i = 0; i < length; i++) {
			sums[i + 1] = sums[i] + A[i];
		}

		int result = Integer.MAX_VALUE;
		Deque<Integer> indices = new LinkedList<>();
		for (int i = 0; i < sums.length; i++) {
			while (!indices.isEmpty() && sums[i] - sums[indices.peekFirst()] >= K) {
				result = Math.min(result, i - indices.pollFirst());
			}

			while (!indices.isEmpty() && sums[i] <= sums[indices.peekLast()]) {
				indices.pollLast();
			}

			indices.offerLast(i);
		}
		return result == Integer.MAX_VALUE ? -1 : result;
	}
}
