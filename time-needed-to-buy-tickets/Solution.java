import java.util.stream.IntStream;

class Solution {
  public int timeRequiredToBuy(int[] tickets, int k) {
    return IntStream.range(0, tickets.length)
        .map(i -> Math.min(tickets[i], tickets[k] - ((i <= k) ? 0 : 1)))
        .sum();
  }
}
