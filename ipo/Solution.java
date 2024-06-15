import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

class Solution {
  public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
    int[] sortedIndices =
        IntStream.range(0, profits.length)
            .boxed()
            .sorted(Comparator.comparing(i -> capital[i]))
            .mapToInt(Integer::intValue)
            .toArray();

    int result = w;
    int index = 0;
    PriorityQueue<Integer> readyProfits = new PriorityQueue<>(Comparator.reverseOrder());
    for (int i = 0; i < k; ++i) {
      while (index != sortedIndices.length && capital[sortedIndices[index]] <= result) {
        readyProfits.offer(profits[sortedIndices[index]]);
        ++index;
      }
      if (readyProfits.isEmpty()) {
        break;
      }

      result += readyProfits.poll();
    }

    return result;
  }
}
