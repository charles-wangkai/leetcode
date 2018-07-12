import java.util.Random;

public class Solution {
	private Random random = new Random();

	int[] nums;

	public Solution(int[] nums) {
		this.nums = nums;
	}

	/** Resets the array to its original configuration and return it. */
	public int[] reset() {
		return nums;
	}

	/** Returns a random shuffling of the array. */
	public int[] shuffle() {
		int[] shuffled = copy(nums);
		permute(shuffled, 0);
		return shuffled;
	}

	private void permute(int[] shuffled, int index) {
		if (index == shuffled.length) {
			return;
		}

		int chosenIndex = randomChoose(index, shuffled.length - 1);
		swap(shuffled, index, chosenIndex);
		permute(shuffled, index + 1);
	}

	private void swap(int[] a, int index1, int index2) {
		int temp = a[index1];
		a[index1] = a[index2];
		a[index2] = temp;
	}

	private int randomChoose(int begin, int end) {
		return random.nextInt(end - begin + 1) + begin;
	}

	private int[] copy(int[] a) {
		int[] result = new int[a.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = a[i];
		}
		return result;
	}
}

// Your Solution object will be instantiated and called as such:
// Solution obj = new Solution(nums);
// int[] param_1 = obj.reset();
// int[] param_2 = obj.shuffle();
