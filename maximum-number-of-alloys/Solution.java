import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int maxNumberOfAlloys(
      int n,
      int k,
      int budget,
      List<List<Integer>> composition,
      List<Integer> stock,
      List<Integer> cost) {
    return composition.stream()
        .mapToInt(machine -> computeAlloyNum(budget, machine, stock, cost))
        .max()
        .getAsInt();
  }

  int computeAlloyNum(int budget, List<Integer> machine, List<Integer> stock, List<Integer> cost) {
    int result = -1;
    int lower = 0;
    int upper = stock.stream().mapToInt(Integer::intValue).max().getAsInt() + budget;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(budget, machine, stock, cost, middle)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  boolean check(
      int budget, List<Integer> machine, List<Integer> stock, List<Integer> cost, int alloyNum) {
    return IntStream.range(0, machine.size())
            .mapToLong(
                i -> Math.max(0, (long) alloyNum * machine.get(i) - stock.get(i)) * cost.get(i))
            .sum()
        <= budget;
  }
}
