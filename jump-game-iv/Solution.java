import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Solution {
  public int minJumps(int[] arr) {
    Map<Integer, List<Integer>> valueToIndices = new HashMap<>();
    for (int i = 0; i < arr.length; ++i) {
      valueToIndices.putIfAbsent(arr[i], new ArrayList<>());
      valueToIndices.get(arr[i]).add(i);
    }

    Set<Integer> processedValues = new HashSet<>();
    int[] distances = new int[arr.length];
    Arrays.fill(distances, -1);
    distances[0] = 0;
    Queue<Integer> queue = new ArrayDeque<>();
    queue.offer(0);
    while (!queue.isEmpty()) {
      int index = queue.poll();

      for (int nextIndex : new int[] {index - 1, index + 1}) {
        if (nextIndex >= 0 && nextIndex < distances.length && distances[nextIndex] == -1) {
          distances[nextIndex] = distances[index] + 1;
          queue.offer(nextIndex);
        }
      }

      if (!processedValues.contains(arr[index])) {
        for (int nextIndex : valueToIndices.get(arr[index])) {
          if (distances[nextIndex] == -1) {
            distances[nextIndex] = distances[index] + 1;
            queue.offer(nextIndex);
          }
        }

        processedValues.add(arr[index]);
      }
    }

    return distances[distances.length - 1];
  }
}
