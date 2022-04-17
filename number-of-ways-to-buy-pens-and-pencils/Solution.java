import java.util.stream.IntStream;

class Solution {
  public long waysToBuyPensPencils(int total, int cost1, int cost2) {
    return IntStream.rangeClosed(0, total / cost1)
        .map(i -> (total - i * cost1) / cost2 + 1)
        .asLongStream()
        .sum();
  }
}