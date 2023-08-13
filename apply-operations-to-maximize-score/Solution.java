import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int maximumScore(List<Integer> nums, int k) {
    int[] primeScores = nums.stream().mapToInt(this::computePrimeScore).toArray();
    int[] leftLengths = buildLeftLengths(primeScores);
    int[] rightLengths = buildRightLengths(primeScores);
    int[] sortedIndices =
        IntStream.range(0, primeScores.length)
            .boxed()
            .sorted(Comparator.comparing(nums::get).reversed())
            .mapToInt(Integer::intValue)
            .toArray();

    int result = 1;
    for (int index : sortedIndices) {
      int exponent = (int) Math.min(k, (leftLengths[index] + 1L) * (rightLengths[index] + 1));
      result = multiplyMod(result, powMod(nums.get(index), exponent));
      k -= exponent;
    }

    return result;
  }

  int[] buildRightLengths(int[] primeScores) {
    int[] result = new int[primeScores.length];
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = result.length - 1; i >= 0; --i) {
      while (!stack.isEmpty() && primeScores[stack.peek()] <= primeScores[i]) {
        stack.pop();
      }

      result[i] = (stack.isEmpty() ? result.length : stack.peek()) - i - 1;

      stack.push(i);
    }

    return result;
  }

  int[] buildLeftLengths(int[] primeScores) {
    int[] result = new int[primeScores.length];
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = 0; i < result.length; ++i) {
      while (!stack.isEmpty() && primeScores[stack.peek()] < primeScores[i]) {
        stack.pop();
      }

      result[i] = i - (stack.isEmpty() ? -1 : stack.peek()) - 1;

      stack.push(i);
    }

    return result;
  }

  int powMod(int base, int exponent) {
    int result = 1;
    while (exponent != 0) {
      if (exponent % 2 == 1) {
        result = multiplyMod(result, base);
      }

      base = multiplyMod(base, base);
      exponent /= 2;
    }

    return result;
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }

  int computePrimeScore(int x) {
    int result = 0;
    for (int i = 2; i * i <= x; ++i) {
      if (x % i == 0) {
        ++result;

        while (x % i == 0) {
          x /= i;
        }
      }
    }
    if (x != 1) {
      ++result;
    }

    return result;
  }
}
