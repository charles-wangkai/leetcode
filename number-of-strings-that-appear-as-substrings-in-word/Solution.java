import java.util.Arrays;

class Solution {
  public int numOfStrings(String[] patterns, String word) {
    return (int) Arrays.stream(patterns).filter(word::contains).count();
  }
}
