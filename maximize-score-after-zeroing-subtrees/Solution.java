import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public int maxScore(int[] parent, int[] op, int[] score) {
    int n = parent.length;

    @SuppressWarnings("unchecked")
    List<Integer>[] childLists = new List[n];
    for (int i = 0; i < childLists.length; ++i) {
      childLists[i] = new ArrayList<>();
    }
    for (int i = 1; i < parent.length; ++i) {
      childLists[parent[i]].add(i);
    }

    return search(op, score, childLists, 0);
  }

  int search(int[] op, int[] score, List<Integer>[] childLists, int node) {
    if (childLists[node].isEmpty()) {
      return score[node];
    }

    int[] childResults =
        childLists[node].stream().mapToInt(child -> search(op, score, childLists, child)).toArray();
    int minChildResult = Arrays.stream(childResults).min().getAsInt();

    return score[node]
        + Arrays.stream(childResults).sum()
        - ((op[node] == 1 || minChildResult < 0) ? minChildResult : 0);
  }
}