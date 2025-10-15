import java.util.stream.IntStream;

class Solution {
  public int[] onceTwice(int[] nums) {
    for (int b = 0; ; ++b) {
      int[] counts = new int[2];
      for (int num : nums) {
        ++counts[(num >> b) & 1];
      }

      if (counts[0] % 3 == 1) {
        return new int[] {findOnce(nums, b, 0), findTwice(nums, b, 1)};
      }
      if (counts[0] % 3 == 2) {
        return new int[] {findOnce(nums, b, 1), findTwice(nums, b, 0)};
      }
    }
  }

  int findOnce(int[] nums, int b, int target) {
    int[] counts = buildCounts(nums, b, target);

    return IntStream.range(0, counts.length).filter(i -> counts[i] == 1).map(i -> 1 << i).sum();
  }

  int findTwice(int[] nums, int b, int target) {
    int[] counts = buildCounts(nums, b, target);

    return IntStream.range(0, counts.length).filter(i -> counts[i] == 2).map(i -> 1 << i).sum();
  }

  int[] buildCounts(int[] nums, int b, int target) {
    int[] counts = new int[32];
    for (int num : nums) {
      if (((num >> b) & 1) == target) {
        for (int i = 0; i < counts.length; ++i) {
          counts[i] = (counts[i] + ((num >> i) & 1)) % 3;
        }
      }
    }

    return counts;
  }
}