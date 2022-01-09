import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int earliestFullBloom(int[] plantTime, int[] growTime) {
    int[] sortedIndices =
        IntStream.range(0, plantTime.length)
            .boxed()
            .sorted(Comparator.comparing((Integer i) -> growTime[i]).reversed())
            .mapToInt(x -> x)
            .toArray();

    int result = 0;
    int time = 0;
    for (int index : sortedIndices) {
      result = Math.max(result, time + plantTime[index] + growTime[index]);
      time += plantTime[index];
    }

    return result;
  }
}