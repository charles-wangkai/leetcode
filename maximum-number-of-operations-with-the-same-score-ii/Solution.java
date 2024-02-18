import java.util.stream.IntStream;

class Solution {
  public int maxOperations(int[] nums) {
    return IntStream.of(
            nums[0] + nums[1],
            nums[nums.length - 2] + nums[nums.length - 1],
            nums[0] + nums[nums.length - 1])
        .map(sum -> search(new int[nums.length][nums.length], sum, nums, 0, nums.length - 1))
        .max()
        .getAsInt();
  }

  int search(int[][] cache, int sum, int[] nums, int beginIndex, int endIndex) {
    if (beginIndex >= endIndex) {
      return 0;
    }

    if (cache[beginIndex][endIndex] == 0) {
      int result = 0;
      if (nums[beginIndex] + nums[beginIndex + 1] == sum) {
        result = Math.max(result, 1 + search(cache, sum, nums, beginIndex + 2, endIndex));
      }
      if (nums[endIndex - 1] + nums[endIndex] == sum) {
        result = Math.max(result, 1 + search(cache, sum, nums, beginIndex, endIndex - 2));
      }
      if (nums[beginIndex] + nums[endIndex] == sum) {
        result = Math.max(result, 1 + search(cache, sum, nums, beginIndex + 1, endIndex - 1));
      }

      cache[beginIndex][endIndex] = result;
    }

    return cache[beginIndex][endIndex];
  }
}
