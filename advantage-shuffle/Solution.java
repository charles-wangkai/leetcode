import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Queue;
import java.util.stream.IntStream;

class Solution {
  public int[] advantageCount(int[] A, int[] B) {
    int[] targetIndices =
        IntStream.range(0, B.length)
            .boxed()
            .sorted(Comparator.comparing(i -> B[i]))
            .mapToInt(x -> x)
            .toArray();

    Queue<Integer> rest = new ArrayDeque<>(Arrays.stream(A).sorted().boxed().toList());
    Queue<Integer> extras = new ArrayDeque<>();

    int[] result = new int[A.length];
    for (int targetIndex : targetIndices) {
      while (!rest.isEmpty() && rest.peek() <= B[targetIndex]) {
        extras.offer(rest.poll());
      }

      result[targetIndex] = rest.isEmpty() ? extras.poll() : rest.poll();
    }

    return result;
  }
}
