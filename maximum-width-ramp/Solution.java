import java.util.Stack;

public class Solution {
	public int maxWidthRamp(int[] A) {
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < A.length; i++) {
			if (stack.empty() || A[i] < A[stack.peek()]) {
				stack.push(i);
			}
		}

		int result = 0;
		for (int i = A.length - 1; i > result; i--) {
			while (!stack.empty() && A[i] >= A[stack.peek()]) {
				result = Math.max(result, i - stack.pop());
			}
		}
		return result;
	}
}
