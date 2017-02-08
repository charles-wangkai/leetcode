import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElement_II {
	public int[] nextGreaterElements(int[] nums) {
		int[] result = new int[nums.length];
		Arrays.fill(result, -1);

		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < nums.length * 2; i++) {
			int index = i % nums.length;

			while (!stack.empty() && nums[index] > nums[stack.peek()]) {
				result[stack.pop()] = nums[index];
			}

			stack.push(index);
		}

		return result;
	}
}
