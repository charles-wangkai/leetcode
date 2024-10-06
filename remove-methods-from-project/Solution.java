import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] invocation : invocations) {
      adjLists[invocation[0]].add(invocation[1]);
    }

    boolean[] visited = new boolean[n];
    search(adjLists, visited, k);

    return Arrays.stream(invocations)
            .anyMatch(invocation -> !visited[invocation[0]] && visited[invocation[1]])
        ? IntStream.range(0, n).boxed().toList()
        : IntStream.range(0, n).filter(i -> !visited[i]).boxed().toList();
  }

  void search(List<Integer>[] adjLists, boolean[] visited, int node) {
    if (!visited[node]) {
      visited[node] = true;

      for (int adj : adjLists[node]) {
        search(adjLists, visited, adj);
      }
    }
  }
}