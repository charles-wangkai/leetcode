import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public long getMaxFunctionValue(List<Integer> receiver, long k) {
    int n = receiver.size();

    long[] sums = IntStream.range(0, n).asLongStream().toArray();
    int[] transitionTos = receiver.stream().mapToInt(Integer::intValue).toArray();
    long[] transitionSums = receiver.stream().mapToInt(Integer::intValue).asLongStream().toArray();
    while (k != 0) {
      if (k % 2 == 1) {
        long[] nextSums = new long[n];
        for (int i = 0; i < sums.length; ++i) {
          nextSums[transitionTos[i]] =
              Math.max(nextSums[transitionTos[i]], sums[i] + transitionSums[i]);
        }
        sums = nextSums;
      }

      int[] nextTransitionTos = new int[n];
      long[] nextTransitionSums = new long[n];
      for (int i = 0; i < n; ++i) {
        nextTransitionTos[i] = transitionTos[transitionTos[i]];
        nextTransitionSums[i] = transitionSums[i] + transitionSums[transitionTos[i]];
      }
      transitionTos = nextTransitionTos;
      transitionSums = nextTransitionSums;

      k /= 2;
    }

    return Arrays.stream(sums).max().getAsLong();
  }
}
