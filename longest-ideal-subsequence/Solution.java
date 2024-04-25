import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  static final int ALPHABET_SIZE = 26;

  public int longestIdealString(String s, int k) {
    int[] lengths = new int[ALPHABET_SIZE];
    for (char c : s.toCharArray()) {
      lengths[c - 'a'] =
          IntStream.range(0, lengths.length)
              .filter(i -> Math.abs(i - (c - 'a')) <= k)
              .map(i -> 1 + lengths[i])
              .max()
              .getAsInt();
    }

    return Arrays.stream(lengths).max().getAsInt();
  }
}