public class ArrayNesting {
	public int arrayNesting(int[] nums) {
		int maxLength = 0;
		boolean[] visited = new boolean[nums.length];
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) {
				maxLength = Math.max(maxLength, search(nums, visited, i));
			}
		}
		return maxLength;
	}

	int search(int[] nums, boolean[] visited, int index) {
		int length = 0;
		while (!visited[index]) {
			visited[index] = true;
			length++;
			index = nums[index];
		}
		return length;
	}
}
