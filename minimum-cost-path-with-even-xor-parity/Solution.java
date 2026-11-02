import java.util.ArrayList;
import java.util.List;

class Solution {
  public int minActivationCost(int[][] edges, int[] values) {
    int n = edges.length + 1;

    @SuppressWarnings("unchecked")
    List<Integer>[] costLists = new List[n];
    for (int i = 0; i < costLists.length; ++i) {
      costLists[i] = new ArrayList<>();
    }

    int result = Integer.MAX_VALUE;
    for (int[] edge : edges) {
      int parity1 = Integer.bitCount(values[edge[0]]) % 2;
      int parity2 = Integer.bitCount(values[edge[1]]) % 2;
      if (parity1 == parity2) {
        result = Math.min(result, edge[2]);
      } else {
        costLists[(parity1 == 0) ? edge[0] : edge[1]].add(edge[2]);
      }
    }

    for (List<Integer> costList : costLists) {
      if (costList.size() >= 2) {
        result =
            Math.min(result, costList.stream().mapToInt(Integer::intValue).sorted().limit(2).sum());
      }
    }

    return (result == Integer.MAX_VALUE) ? -1 : result;
  }
}