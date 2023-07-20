// https://www.geeksforgeeks.org/hierholzers-algorithm-directed-graph/

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int[][] validArrangement(int[][] pairs) {
    int start = findStart(pairs);

    Map<Integer, List<Integer>> fromToTos = new HashMap<>();
    for (int[] pair : pairs) {
      if (!fromToTos.containsKey(pair[0])) {
        fromToTos.put(pair[0], new ArrayList<>());
      }
      fromToTos.get(pair[0]).add(pair[1]);
    }

    List<Integer> path = new ArrayList<>();
    Deque<Integer> stack = new ArrayDeque<>();
    int current = start;
    stack.push(current);

    while (!stack.isEmpty()) {
      List<Integer> tos = fromToTos.getOrDefault(current, List.of());

      if (tos.isEmpty()) {
        path.add(current);
        current = stack.pop();
      } else {
        stack.push(current);
        current = tos.remove(tos.size() - 1);
      }
    }
    Collections.reverse(path);

    return IntStream.range(0, path.size() - 1)
        .mapToObj(i -> new int[] {path.get(i), path.get(i + 1)})
        .toArray(int[][]::new);
  }

  int findStart(int[][] pairs) {
    Map<Integer, Integer> valueToDelta = new HashMap<>();
    for (int[] pair : pairs) {
      valueToDelta.put(pair[0], valueToDelta.getOrDefault(pair[0], 0) + 1);
      valueToDelta.put(pair[1], valueToDelta.getOrDefault(pair[1], 0) - 1);
    }

    return valueToDelta.keySet().stream()
        .filter(value -> valueToDelta.get(value) == 1)
        .findAny()
        .orElse(pairs[0][0]);
  }
}
