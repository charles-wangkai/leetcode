import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int[] getFinalState(int[] nums, int k, int multiplier) {
    if (multiplier == 1) {
      return nums;
    }

    long[] values = Arrays.stream(nums).asLongStream().toArray();
    Comparator<Integer> indexComparator =
        Comparator.<Integer, Long>comparing(i -> values[i]).thenComparing(i -> i);
    int target = Arrays.stream(nums).max().getAsInt() + 1;

    PriorityQueue<Integer> pq = new PriorityQueue<>(indexComparator);
    for (int i = 0; i < values.length; ++i) {
      pq.offer(i);
    }

    while (k != 0 && values[pq.peek()] < target) {
      int index = pq.poll();
      values[index] *= multiplier;
      pq.offer(index);

      --k;
    }

    int commonFactor = powMod(multiplier, k / nums.length);
    int[] result =
        Arrays.stream(values)
            .mapToInt(x -> multiplyMod(commonFactor, (int) (x % MODULUS)))
            .toArray();

    int[] sortedIndices =
        IntStream.range(0, values.length)
            .boxed()
            .sorted(indexComparator)
            .mapToInt(Integer::intValue)
            .toArray();
    for (int i = 0; i < k % nums.length; ++i) {
      result[sortedIndices[i]] = multiplyMod(result[sortedIndices[i]], multiplier);
    }

    return result;
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }

  int powMod(int base, int exponent) {
    return (exponent == 0)
        ? 1
        : multiplyMod(
            (exponent % 2 == 0) ? 1 : base, powMod(multiplyMod(base, base), exponent / 2));
  }
}