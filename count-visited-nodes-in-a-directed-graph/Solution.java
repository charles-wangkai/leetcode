import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public int[] countVisitedNodes(List<Integer> edges) {
    int[] result = new int[edges.size()];
    for (int i = 0; i < result.length; ++i) {
      if (result[i] == 0) {
        List<Integer> nodes = new ArrayList<>();
        Map<Integer, Integer> nodeToIndex = new HashMap<>();
        int current = i;
        while (true) {
          if (result[current] != 0) {
            for (int j = 0; j < nodes.size(); ++j) {
              result[nodes.get(j)] = result[current] + (nodes.size() - j);
            }

            break;
          }
          if (nodeToIndex.containsKey(current)) {
            for (int j = 0; j < nodeToIndex.get(current); ++j) {
              result[nodes.get(j)] = nodes.size() - j;
            }
            for (int j = nodeToIndex.get(current); j < nodes.size(); ++j) {
              result[nodes.get(j)] = nodes.size() - nodeToIndex.get(current);
            }

            break;
          }

          nodes.add(current);
          nodeToIndex.put(current, nodes.size() - 1);

          current = edges.get(current);
        }
      }
    }

    return result;
  }
}
