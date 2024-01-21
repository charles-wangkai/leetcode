import java.util.stream.IntStream;

class Solution {
  public int minimumPushes(String word) {
    return IntStream.range(0, word.length()).map(i -> i / 8 + 1).sum();
  }
}