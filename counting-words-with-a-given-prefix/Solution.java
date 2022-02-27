import java.util.Arrays;

class Solution {
  public int prefixCount(String[] words, String pref) {
    return (int) Arrays.stream(words).filter(word -> word.startsWith(pref)).count();
  }
}