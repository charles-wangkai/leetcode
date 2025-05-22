import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

class Solution {
  public int maxRemoval(int[] nums, int[][] queries) {
    int[] sortedIndices =
        IntStream.range(0, queries.length)
            .boxed()
            .sorted(Comparator.comparing(i -> queries[i][0]))
            .mapToInt(Integer::intValue)
            .toArray();

    int chosenCount = 0;
    PriorityQueue<Integer> chosenLastIndices = new PriorityQueue<>();
    PriorityQueue<Integer> availableLastIndices = new PriorityQueue<>(Comparator.reverseOrder());
    int index = 0;
    for (int i = 0; i < nums.length; ++i) {
      while (index != sortedIndices.length && queries[sortedIndices[index]][0] == i) {
        availableLastIndices.offer(queries[sortedIndices[index]][1]);
        ++index;
      }

      while (!chosenLastIndices.isEmpty() && chosenLastIndices.peek() < i) {
        chosenLastIndices.poll();
      }

      while (chosenLastIndices.size() < nums[i]) {
        if (availableLastIndices.isEmpty() || availableLastIndices.peek() < i) {
          return -1;
        }

        chosenLastIndices.offer(availableLastIndices.poll());
        ++chosenCount;
      }
    }

    return queries.length - chosenCount;
  }
}