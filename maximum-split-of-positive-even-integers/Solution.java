import java.util.ArrayList;
import java.util.List;

class Solution {
  public List<Long> maximumEvenSplit(long finalSum) {
    if (finalSum % 2 != 0) {
      return List.of();
    }

    long rest = finalSum;
    List<Long> result = new ArrayList<>();
    for (int i = 2; 2 * i < rest; i += 2) {
      result.add((long) i);
      rest -= i;
    }
    result.add(rest);

    return result;
  }
}