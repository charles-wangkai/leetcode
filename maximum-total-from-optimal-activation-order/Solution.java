import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Queue;
import java.util.stream.IntStream;

class Solution {
  public long maxTotal(int[] value, int[] limit) {
    int[] sortedIndices =
        IntStream.range(0, value.length)
            .boxed()
            .sorted(
                Comparator.<Integer, Integer>comparing(i -> limit[i])
                    .thenComparing(
                        Comparator.<Integer, Integer>comparing(i -> value[i]).reversed()))
            .mapToInt(Integer::intValue)
            .toArray();

    long result = 0;
    Queue<Integer> activeIndices = new ArrayDeque<>();
    int pos = 0;
    while (pos != sortedIndices.length) {
      if (limit[sortedIndices[pos]] > activeIndices.size()) {
        result += value[sortedIndices[pos]];
        activeIndices.offer(sortedIndices[pos]);
        ++pos;

        int x = activeIndices.size();
        while (!activeIndices.isEmpty() && limit[activeIndices.peek()] == x) {
          activeIndices.poll();
        }
        while (pos != sortedIndices.length && limit[sortedIndices[pos]] == x) {
          ++pos;
        }
      }
    }

    return result;
  }
}