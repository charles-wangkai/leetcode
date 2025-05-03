import java.util.stream.IntStream;

class Solution {
  public int minDominoRotations(int[] tops, int[] bottoms) {
    return IntStream.rangeClosed(1, 6)
        .flatMap(
            target ->
                IntStream.of(
                    computeRotationNum(tops, bottoms, target),
                    computeRotationNum(bottoms, tops, target)))
        .filter(target -> target != Integer.MAX_VALUE)
        .min()
        .orElse(-1);
  }

  int computeRotationNum(int[] primary, int[] secondary, int target) {
    int result = 0;
    for (int i = 0; i < primary.length; ++i) {
      if (primary[i] != target) {
        if (secondary[i] != target) {
          return Integer.MAX_VALUE;
        }

        ++result;
      }
    }

    return result;
  }
}
