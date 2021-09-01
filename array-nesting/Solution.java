class Solution {
  public int arrayNesting(int[] nums) {
    int maxLength = 0;
    boolean[] visited = new boolean[nums.length];
    for (int i = 0; i < visited.length; ++i) {
      if (!visited[i]) {
        maxLength = Math.max(maxLength, computeSetSize(nums, visited, i));
      }
    }

    return maxLength;
  }

  int computeSetSize(int[] nums, boolean[] visited, int index) {
    int size = 0;
    while (!visited[index]) {
      visited[index] = true;
      ++size;
      index = nums[index];
    }

    return size;
  }
}
