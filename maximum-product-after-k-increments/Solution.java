import java.util.PriorityQueue;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int maximumProduct(int[] nums, int k) {
    PriorityQueue<Integer> values = new PriorityQueue<>();
    for (int num : nums) {
      values.offer(num);
    }

    for (int i = 0; i < k; ++i) {
      values.offer(values.poll() + 1);
    }

    return values.stream().reduce(this::multiplyMod).get();
  }

  int multiplyMod(int x, int y) {
    return (int) ((long) x * y % MODULUS);
  }
}