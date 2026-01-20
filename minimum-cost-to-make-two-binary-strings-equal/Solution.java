import java.util.stream.IntStream;

class Solution {
  public long minimumCost(String s, String t, int flipCost, int swapCost, int crossCost) {
    int count01 =
        (int)
            IntStream.range(0, s.length())
                .filter(i -> s.charAt(i) == '0' && t.charAt(i) == '1')
                .count();
    int count10 =
        (int)
            IntStream.range(0, s.length())
                .filter(i -> s.charAt(i) == '1' && t.charAt(i) == '0')
                .count();

    if (flipCost * 2 <= swapCost) {
      return (long) (count01 + count10) * flipCost;
    }

    int minDiffCount = Math.min(count01, count10);
    int maxDiffCount = Math.max(count01, count10);

    return (long) minDiffCount * swapCost
        + (maxDiffCount - minDiffCount) / 2L * Math.min(flipCost * 2, crossCost + swapCost)
        + (maxDiffCount - minDiffCount) % 2 * flipCost;
  }
}