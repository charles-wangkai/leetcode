import java.util.Arrays;

class Solution {
  public boolean makeEqual(String[] words) {
    int[] counts = new int[26];
    for (String word : words) {
      for (char ch : word.toCharArray()) {
        ++counts[ch - 'a'];
      }
    }

    return Arrays.stream(counts).allMatch(count -> count % words.length == 0);
  }
}
