import java.util.List;
import java.util.stream.IntStream;

class Solution {
  static final int BIT_NUM = 30;
  static final int MODULUS = 1_000_000_007;

  public int maxSum(List<Integer> nums, int k) {
    int[] values = new int[nums.size()];
    for (int b = 0; b < BIT_NUM; ++b) {
      int b_ = b;
      for (int i = (int) nums.stream().filter(x -> ((x >> b_) & 1) == 1).count() - 1; i >= 0; --i) {
        values[i] |= 1 << b;
      }
    }

    return IntStream.range(0, k)
        .map(i -> multiplyMod(values[i], values[i]))
        .reduce(this::addMod)
        .getAsInt();
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}
