import java.util.stream.IntStream;

class Solution {
  public int minimumMountainRemovals(int[] nums) {
    int[] leftRemoveNums = buildRemoveNums(nums);
    int[] rightRemoveNums = reverse(buildRemoveNums(reverse(nums)));

    return IntStream.range(0, nums.length)
        .filter(i -> leftRemoveNums[i] != i && rightRemoveNums[i] != nums.length - 1 - i)
        .map(i -> leftRemoveNums[i] + rightRemoveNums[i])
        .min()
        .getAsInt();
  }

  int[] reverse(int[] a) {
    return IntStream.range(0, a.length).map(i -> a[a.length - 1 - i]).toArray();
  }

  int[] buildRemoveNums(int[] a) {
    int[] result = new int[a.length];
    for (int i = 0; i < result.length; ++i) {
      result[i] = i;
      for (int j = 0; j < i; ++j) {
        if (a[j] < a[i]) {
          result[i] = Math.min(result[i], result[j] + (i - j - 1));
        }
      }
    }

    return result;
  }
}
