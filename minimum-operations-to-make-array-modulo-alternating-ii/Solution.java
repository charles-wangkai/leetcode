import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public long minOperations(int[] nums, int k) {
    if (nums.length == 1) {
      return 0;
    }

    Element[] evenElements =
        buildElements(
            k, IntStream.range(0, nums.length).filter(i -> i % 2 == 0).map(i -> nums[i]).toArray());
    Element[] oddElements =
        buildElements(
            k, IntStream.range(0, nums.length).filter(i -> i % 2 == 1).map(i -> nums[i]).toArray());

    long result = Long.MAX_VALUE;
    for (Element evenElement : evenElements) {
      for (Element oddElement : oddElements) {
        if (oddElement.remainder() != evenElement.remainder()) {
          result = Math.min(result, evenElement.operationNum() + oddElement.operationNum());
        }
      }
    }

    return result;
  }

  Element[] buildElements(int k, int[] values) {
    int[] remainders = Arrays.stream(values).map(value -> value % k).toArray();

    int half = k / 2;

    int[] secondDiffs = new int[k];
    for (int remainder : remainders) {
      fill(secondDiffs, remainder - half + 1, remainder, -1);
      fill(secondDiffs, remainder + 1, remainder + half, 1);
    }

    int[] firstDiffs =
        Arrays.stream(upgrade(secondDiffs[0], secondDiffs)).mapToInt(Math::toIntExact).toArray();

    long[] operationNums =
        upgrade(
            Arrays.stream(remainders)
                .map(remainder -> Math.min(remainder, k - remainder))
                .asLongStream()
                .sum(),
            firstDiffs);

    return IntStream.range(0, operationNums.length)
        .boxed()
        .sorted(Comparator.comparing(remainder -> operationNums[remainder]))
        .limit(2)
        .map(remainder -> new Element(remainder, operationNums[remainder]))
        .toArray(Element[]::new);
  }

  void fill(int[] secondDiffs, int begin, int end, int diff) {
    int k = secondDiffs.length;

    int beginIndex = Math.floorMod(begin, k);
    int endIndex = Math.floorMod(end, k);
    if (beginIndex <= endIndex) {
      secondDiffs[beginIndex] += diff;

      if (endIndex != secondDiffs.length - 1) {
        secondDiffs[endIndex + 1] -= diff;
      }
    } else {
      secondDiffs[beginIndex] += diff;

      secondDiffs[0] += diff;
      secondDiffs[endIndex + 1] -= diff;
    }
  }

  long[] upgrade(long first, int[] diffs) {
    long[] result = new long[diffs.length];
    result[0] = first;
    for (int i = 1; i < result.length; ++i) {
      result[i] = result[i - 1] + diffs[i];
    }

    return result;
  }
}

record Element(int remainder, long operationNum) {}
