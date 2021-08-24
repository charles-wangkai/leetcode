import java.util.stream.IntStream;

class Solution {
  public int minTimeToType(String word) {
    return word.length()
        + IntStream.range(0, word.length())
            .map(
                i -> {
                  int diff = Math.abs(word.charAt(i) - ((i == 0) ? 'a' : word.charAt(i - 1)));

                  return Math.min(diff, 26 - diff);
                })
            .sum();
  }
}
