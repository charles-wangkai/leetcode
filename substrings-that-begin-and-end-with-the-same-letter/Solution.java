import java.util.Arrays;

class Solution {
  static final int ALPHABET_SIZE = 26;

  public long numberOfSubstrings(String s) {
    int[] counts = new int[ALPHABET_SIZE];
    for (char c : s.toCharArray()) {
      ++counts[c - 'a'];
    }

    return Arrays.stream(counts).mapToLong(c -> c + c * (c - 1L) / 2).sum();
  }
}