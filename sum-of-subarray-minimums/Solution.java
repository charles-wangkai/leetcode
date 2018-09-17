import java.util.Stack;

public class Solution {
	static final int MOD_DIVISOR = 1_000_000_007;

	public int sumSubarrayMins(int[] A) {
		int[] leftIndices = new int[A.length];
		Stack<Integer> leftAscendingIndices = new Stack<>();
		for (int i = 0; i < leftIndices.length; i++) {
			while (!leftAscendingIndices.empty() && A[i] <= A[leftAscendingIndices.peek()]) {
				leftAscendingIndices.pop();
			}

			leftIndices[i] = (leftAscendingIndices.empty() ? -1 : leftAscendingIndices.peek()) + 1;
			leftAscendingIndices.push(i);
		}

		int[] rightIndices = new int[A.length];
		Stack<Integer> rightAscendingIndices = new Stack<>();
		for (int i = rightIndices.length - 1; i >= 0; i--) {
			while (!rightAscendingIndices.empty() && A[i] < A[rightAscendingIndices.peek()]) {
				rightAscendingIndices.pop();
			}

			rightIndices[i] = (rightAscendingIndices.empty() ? rightIndices.length : rightAscendingIndices.peek()) - 1;
			rightAscendingIndices.push(i);
		}

		int result = 0;
		for (int i = 0; i < A.length; i++) {
			result = addMod(result, multiplyMod(A[i], multiplyMod(i - leftIndices[i] + 1, rightIndices[i] - i + 1)));
		}
		return result;
	}

	int addMod(int x, int y) {
		return (x + y) % MOD_DIVISOR;
	}

	int multiplyMod(int x, int y) {
		return (int) ((long) x * y % MOD_DIVISOR);
	}
}
