import java.util.Arrays;

class Solution {
  public long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {
    int[] sorted = Arrays.stream(flowers).boxed().sorted().mapToInt(x -> x).toArray();

    long[] gaps = new long[sorted.length];
    for (int i = 1; i < gaps.length; ++i) {
      gaps[i] = gaps[i - 1] + (long) (sorted[i] - sorted[i - 1]) * i;
    }

    long result = -1;
    long completeNeeded = 0;
    for (int endIndex = sorted.length - 1; endIndex >= -1; --endIndex) {
      if (endIndex + 1 != sorted.length) {
        completeNeeded += Math.max(0, target - sorted[endIndex + 1]);
      }
      if (completeNeeded > newFlowers) {
        break;
      }

      if (endIndex == -1 || sorted[endIndex] < target) {
        int incompleteNum = endIndex + 1;
        long rest = newFlowers - completeNeeded;
        int index = find(gaps, endIndex, rest);

        result =
            Math.max(
                result,
                (long) full * (sorted.length - incompleteNum)
                    + ((incompleteNum == 0)
                        ? 0
                        : (long) partial
                            * Math.min(
                                target - 1, sorted[index] + (rest - gaps[index]) / (index + 1))));
      }
    }

    return result;
  }

  int find(long[] gaps, int endIndex, long rest) {
    int result = 0;
    int lowerIndex = 0;
    int upperIndex = endIndex;
    while (lowerIndex <= upperIndex) {
      int middleIndex = (lowerIndex + upperIndex) / 2;
      if (gaps[middleIndex] <= rest) {
        result = middleIndex;
        lowerIndex = middleIndex + 1;
      } else {
        upperIndex = middleIndex - 1;
      }
    }

    return result;
  }
}