import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int maxScore(int[] nums) {
    return search(nums, new boolean[nums.length], new int[nums.length / 2], 0);
  }

  int search(int[] nums, boolean[] used, int[] gcds, int index) {
    if (index == gcds.length) {
      int[] sorted = Arrays.stream(gcds).sorted().toArray();

      return IntStream.range(0, sorted.length).map(i -> (i + 1) * sorted[i]).sum();
    }

    int i1 = 0;
    while (used[i1]) {
      ++i1;
    }
    used[i1] = true;

    int result = 0;
    for (int i2 = i1 + 1; i2 < used.length; ++i2) {
      if (!used[i2]) {
        used[i2] = true;

        gcds[index] = gcd(nums[i1], nums[i2]);
        result = Math.max(result, search(nums, used, gcds, index + 1));

        used[i2] = false;
      }
    }

    used[i1] = false;

    return result;
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}
