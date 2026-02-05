import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  static final int BIT_NUM = 30;

  public int longestSubsequence(int[] nums) {
    return IntStream.range(0, BIT_NUM)
        .map(
            b ->
                computeLongestIncreasingSubsequenceLength(
                    Arrays.stream(nums).filter(x -> ((x >> b) & 1) == 1).toArray()))
        .max()
        .getAsInt();
  }

  int computeLongestIncreasingSubsequenceLength(int[] a) {
    List<Integer> tails = new ArrayList<>();
    for (int x : a) {
      int index = Collections.binarySearch(tails, x);
      if (index < 0) {
        index = -1 - index;
      }

      if (index == tails.size()) {
        tails.add(x);
      } else {
        tails.set(index, x);
      }
    }

    return tails.size();
  }
}