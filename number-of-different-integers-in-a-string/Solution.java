import java.util.Arrays;

class Solution {
  public int numDifferentIntegers(String word) {
    return (int)
        Arrays.stream(word.split("[a-z]+"))
            .filter(s -> !s.isEmpty())
            .map(s -> s.replaceAll("^0+", ""))
            .distinct()
            .count();
  }
}
