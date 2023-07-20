import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Solution {
  public int minimumSemesters(int N, int[][] relations) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[N];
    for (int i = 0; i < adjLists.length; i++) {
      adjLists[i] = new ArrayList<>();
    }

    for (int[] relation : relations) {
      adjLists[relation[0] - 1].add(relation[1] - 1);
    }

    List<Integer> sorted = topologicalSort(adjLists);
    if (sorted == null) {
      return -1;
    }

    int[] earliests = new int[N];
    for (int node : sorted) {
      for (int adj : adjLists[node]) {
        earliests[adj] = Math.max(earliests[adj], earliests[node] + 1);
      }
    }

    return Arrays.stream(earliests).max().getAsInt() + 1;
  }

  List<Integer> topologicalSort(List<Integer>[] adjLists) {
    int N = adjLists.length;

    int[] inDegrees = new int[N];
    for (List<Integer> adjList : adjLists) {
      for (int adj : adjList) {
        inDegrees[adj]++;
      }
    }

    List<Integer> sorted = new ArrayList<>();
    Deque<Integer> starts = new ArrayDeque<>();
    for (int i = 0; i < inDegrees.length; i++) {
      if (inDegrees[i] == 0) {
        starts.push(i);
      }
    }

    while (!starts.isEmpty()) {
      int start = starts.pop();
      sorted.add(start);

      for (int adj : adjLists[start]) {
        inDegrees[adj]--;

        if (inDegrees[adj] == 0) {
          starts.add(adj);
        }
      }
    }

    return (sorted.size() == N) ? sorted : null;
  }
}
