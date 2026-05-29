import java.util.stream.IntStream;

class Solution {
  public int[] limitOccurrences(int[] nums, int k) {
    int[] sequences = new int[nums.length];
    for (int i = 0; i < sequences.length; ++i) {
      sequences[i] = (i == 0 || nums[i] != nums[i - 1]) ? 0 : (sequences[i - 1] + 1);
    }

    return IntStream.range(0, nums.length)
        .filter(i -> sequences[i] < k)
        .map(i -> nums[i])
        .toArray();
  }
}