import java.util.Arrays;
import java.util.Stack;

public class Solution {
	public int numSubarrayBoundedMax(int[] A, int L, int R) {
		int[] leftIndices = new int[A.length];
		Stack<Integer> leftMaxs = new Stack<Integer>();
		for (int i = 0; i < A.length; i++) {
			while (!leftMaxs.empty() && A[leftMaxs.peek()] < A[i]) {
				leftMaxs.pop();
			}

			leftIndices[i] = leftMaxs.empty() ? 0 : (leftMaxs.peek() + 1);

			leftMaxs.push(i);
		}
		System.out.println(Arrays.toString(leftIndices));

		int[] rightIndices = new int[A.length];
		Stack<Integer> rightMaxs = new Stack<Integer>();
		for (int i = A.length - 1; i >= 0; i--) {
			while (!rightMaxs.empty() && A[rightMaxs.peek()] <= A[i]) {
				rightMaxs.pop();
			}

			rightIndices[i] = rightMaxs.empty() ? (A.length - 1) : (rightMaxs.peek() - 1);

			rightMaxs.push(i);
		}

		int result = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] >= L && A[i] <= R) {
				result += (i - leftIndices[i] + 1) * (rightIndices[i] - i + 1);
			}
		}
		return result;
	}
}
