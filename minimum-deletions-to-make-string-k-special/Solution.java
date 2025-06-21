import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int minimumDeletions(String word, int k) {
    int[] counts = new int[26];
    for (char c : word.toCharArray()) {
      ++counts[c - 'a'];
    }

    int maxCount = Arrays.stream(counts).max().getAsInt();

    return IntStream.rangeClosed(0, maxCount)
        .map(
            i ->
                Arrays.stream(counts)
                    .map(
                        count -> {
                          if (count > i) {
                            return count - i;
                          }
                          if (count < i - k) {
                            return count;
                          }

                          return 0;
                        })
                    .sum())
        .min()
        .getAsInt();
  }
}