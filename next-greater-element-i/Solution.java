import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution {
	public int[] nextGreaterElement(int[] findNums, int[] nums) {
		Map<Integer, Integer> number2greater = new HashMap<Integer, Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		for (int num : nums) {
			while (!stack.empty() && num > stack.peek()) {
				number2greater.put(stack.pop(), num);
			}

			stack.push(num);
		}

		int[] result = new int[findNums.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = number2greater.containsKey(findNums[i]) ? number2greater.get(findNums[i]) : -1;
		}
		return result;
	}
}
