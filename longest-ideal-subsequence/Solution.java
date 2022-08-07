import java.util.Arrays;

class Solution {
  static final int ALPHABET_SIZE = 26;

  public int longestIdealString(String s, int k) {
    int[] lengths = new int[ALPHABET_SIZE];
    for (char c : s.toCharArray()) {
      int index = c - 'a';
      int length = 1;
      for (int i = 0; i < lengths.length; ++i) {
        if (Math.abs(i - index) <= k) {
          length = Math.max(length, 1 + lengths[i]);
        }
      }
      lengths[index] = length;
    }

    return Arrays.stream(lengths).max().getAsInt();
  }
}