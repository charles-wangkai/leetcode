import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
    int[] sortedIndices =
        IntStream.range(0, positions.length)
            .boxed()
            .sorted(Comparator.comparing(i -> positions[i]))
            .mapToInt(Integer::intValue)
            .toArray();
    Deque<Integer> stack = new ArrayDeque<>();
    for (int index : sortedIndices) {
      if (directions.charAt(index) == 'L') {
        while (!stack.isEmpty()) {
          int top = stack.pop();
          if (healths[index] < healths[top]) {
            healths[index] = 0;
            --healths[top];
            stack.push(top);

            break;
          } else if (healths[index] > healths[top]) {
            healths[top] = 0;
            --healths[index];
          } else {
            healths[index] = 0;
            healths[top] = 0;

            break;
          }
        }
      } else {
        stack.push(index);
      }
    }

    return Arrays.stream(healths).filter(health -> health != 0).boxed().toList();
  }
}
