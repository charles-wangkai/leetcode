import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

class Solution {
  public int[] getOrder(int[][] tasks) {
    int[] sortedTaskIndices =
        IntStream.range(0, tasks.length)
            .boxed()
            .sorted(Comparator.comparing(i -> tasks[i][0]))
            .mapToInt(x -> x)
            .toArray();

    List<Integer> result = new ArrayList<>();
    int index = 0;
    int time = -1;
    PriorityQueue<Integer> pq =
        new PriorityQueue<>(Comparator.comparing((Integer i) -> tasks[i][1]).thenComparing(i -> i));
    while (index != sortedTaskIndices.length || !pq.isEmpty()) {
      if (pq.isEmpty()) {
        time = Math.max(time, tasks[sortedTaskIndices[index]][0]);
      }

      while (index != sortedTaskIndices.length && tasks[sortedTaskIndices[index]][0] <= time) {
        pq.offer(sortedTaskIndices[index]);
        ++index;
      }

      int taskIndex = pq.poll();
      result.add(taskIndex);
      time += tasks[taskIndex][1];
    }

    return result.stream().mapToInt(x -> x).toArray();
  }
}
