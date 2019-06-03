import java.util.Arrays;
import java.util.Stack;

public class Solution {
	public int validSubarrays(int[] nums) {
		int[] a = Arrays.copyOf(nums, nums.length + 1);
		a[a.length - 1] = Integer.MIN_VALUE;

		Stack<Integer> stack = new Stack<>();
		stack.push(a.length - 1);

		int result = 0;
		for (int i = a.length - 2; i >= 0; i--) {
			while (a[stack.peek()] >= a[i]) {
				stack.pop();
			}

			result += stack.peek() - i;

			stack.push(i);
		}
		return result;
	}
}
