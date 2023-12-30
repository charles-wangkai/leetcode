import java.util.Arrays;

class Solution {
  static final int ALPHABET_SIZE = 26;

  public boolean makeEqual(String[] words) {
    int[] counts = new int[ALPHABET_SIZE];
    for (String word : words) {
      for (char c : word.toCharArray()) {
        ++counts[c - 'a'];
      }
    }

    return Arrays.stream(counts).allMatch(count -> count % words.length == 0);
  }
}
