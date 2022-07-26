import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public String bestHand(int[] ranks, char[] suits) {
    if (IntStream.range(0, suits.length).map(i -> suits[i]).distinct().count() == 1) {
      return "Flush";
    }

    Arrays.sort(ranks);

    if (IntStream.rangeClosed(0, ranks.length - 3).anyMatch(i -> ranks[i] == ranks[i + 2])) {
      return "Three of a Kind";
    }
    if (IntStream.rangeClosed(0, ranks.length - 2).anyMatch(i -> ranks[i] == ranks[i + 1])) {
      return "Pair";
    }

    return "High Card";
  }
}