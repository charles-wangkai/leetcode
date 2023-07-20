import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int totalStrength(int[] strength) {
    int[] leftIndices = buildLeftIndices(strength);
    int[] rightIndices = buildRightIndices(strength);

    int[] prefixSums = buildPrefixSums(strength);

    int[] leftCumulativeSums = buildLeftCumulativeSums(strength);
    int[] rightCumulativeSums = buildRightCumulativeSums(strength);

    return IntStream.range(0, strength.length)
        .map(
            i ->
                multiplyMod(
                    strength[i],
                    addMod(
                        multiplyMod(
                            strength[i],
                            multiplyMod(i - leftIndices[i] + 1, rightIndices[i] - i + 1)),
                        addMod(
                            multiplyMod(
                                i - leftIndices[i] + 1,
                                computeLeftRangeCumulativeSum(
                                    prefixSums, leftCumulativeSums, i + 1, rightIndices[i])),
                            multiplyMod(
                                rightIndices[i] - i + 1,
                                computeRightRangeCumulativeSum(
                                    prefixSums, rightCumulativeSums, leftIndices[i], i - 1))))))
        .reduce(this::addMod)
        .getAsInt();
  }

  int computeLeftRangeCumulativeSum(
      int[] prefixSums, int[] leftCumulativeSums, int beginIndex, int endIndex) {
    if (beginIndex > endIndex) {
      return 0;
    }

    return addMod(
        addMod(
            leftCumulativeSums[endIndex],
            (beginIndex == 0) ? 0 : -leftCumulativeSums[beginIndex - 1]),
        -multiplyMod(
            endIndex - beginIndex + 1, (beginIndex == 0) ? 0 : prefixSums[beginIndex - 1]));
  }

  int computeRightRangeCumulativeSum(
      int[] prefixSums, int[] rightCumulativeSums, int beginIndex, int endIndex) {
    if (beginIndex > endIndex) {
      return 0;
    }

    return addMod(
        addMod(
            rightCumulativeSums[beginIndex],
            (endIndex == prefixSums.length - 1) ? 0 : -rightCumulativeSums[endIndex + 1]),
        -multiplyMod(
            endIndex - beginIndex + 1, prefixSums[prefixSums.length - 1] - prefixSums[endIndex]));
  }

  int[] buildLeftCumulativeSums(int[] strength) {
    int[] result = new int[strength.length];
    int sum = 0;
    for (int i = 0; i < result.length; ++i) {
      sum = addMod(sum, strength[i]);
      result[i] = addMod((i == 0) ? 0 : result[i - 1], sum);
    }

    return result;
  }

  int[] buildRightCumulativeSums(int[] strength) {
    int[] result = new int[strength.length];
    int sum = 0;
    for (int i = result.length - 1; i >= 0; --i) {
      sum = addMod(sum, strength[i]);
      result[i] = addMod((i == result.length - 1) ? 0 : result[i + 1], sum);
    }

    return result;
  }

  int[] buildPrefixSums(int[] strength) {
    int[] result = new int[strength.length];
    for (int i = 0; i < result.length; ++i) {
      result[i] = addMod((i == 0) ? 0 : result[i - 1], strength[i]);
    }

    return result;
  }

  int[] buildLeftIndices(int[] strength) {
    int[] result = new int[strength.length];
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = 0; i < strength.length; ++i) {
      while (!stack.isEmpty() && strength[i] < strength[stack.peek()]) {
        stack.pop();
      }

      result[i] = (stack.isEmpty() ? -1 : stack.peek()) + 1;
      stack.push(i);
    }

    return result;
  }

  int[] buildRightIndices(int[] strength) {
    int[] result = new int[strength.length];
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = strength.length - 1; i >= 0; --i) {
      while (!stack.isEmpty() && strength[i] <= strength[stack.peek()]) {
        stack.pop();
      }

      result[i] = (stack.isEmpty() ? strength.length : stack.peek()) - 1;
      stack.push(i);
    }

    return result;
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}
