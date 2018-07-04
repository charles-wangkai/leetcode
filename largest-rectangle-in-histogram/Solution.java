import java.util.Arrays;
import java.util.Stack;

public class Solution {
	public int largestRectangleArea(int[] height) {
		height = Arrays.copyOf(height, height.length + 1);
		Stack<Integer> indices = new Stack<Integer>();
		int maxArea = 0;
		for (int i = 0; i < height.length; i++) {
			while (!indices.empty() && height[i] <= height[indices.peek()]) {
				int h = height[indices.pop()];
				maxArea = Math.max(maxArea, h
						* (i - (indices.empty() ? 0 : (indices.peek() + 1))));
			}
			indices.push(i);
		}
		return maxArea;
	}
}
