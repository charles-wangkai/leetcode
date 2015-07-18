import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum {
	public int[] maxSlidingWindow(int[] nums, int k) {
		if (nums.length == 0) {
			return new int[0];
		}

		int[] maxs = new int[nums.length - k + 1];
		Deque<Integer> indices = new ArrayDeque<Integer>();
		for (int i = 0; i < nums.length; i++) {
			while (!indices.isEmpty() && indices.peekFirst() <= i - k) {
				indices.pollFirst();
			}
			while (!indices.isEmpty() && nums[indices.peekLast()] <= nums[i]) {
				indices.pollLast();
			}
			indices.offerLast(i);

			if (i - k + 1 >= 0) {
				maxs[i - k + 1] = nums[indices.peekFirst()];
			}
		}
		return maxs;
	}
}
