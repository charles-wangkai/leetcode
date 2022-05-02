import java.util.Arrays;

class Solution {
  public int countPrefixes(String[] words, String s) {
    return (int) Arrays.stream(words).filter(s::startsWith).count();
  }
}