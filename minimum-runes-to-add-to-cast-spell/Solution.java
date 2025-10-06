import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

class Solution {
  public int minRunesToAdd(int n, int[] crystals, int[] flowFrom, int[] flowTo) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < flowFrom.length; ++i) {
      adjLists[flowFrom[i]].add(flowTo[i]);
    }

    List<Integer> sorted =
        Stream.concat(Arrays.stream(crystals).boxed(), topologicalSort(adjLists).stream()).toList();

    boolean[] reaches = new boolean[n];
    for (int crystal : crystals) {
      reaches[crystal] = true;
    }

    int result = 0;
    for (int node : sorted) {
      if (!reaches[node]) {
        ++result;
      }

      search1(adjLists, reaches, node);
    }

    return result;
  }

  void search1(List<Integer>[] adjLists, boolean[] reaches, int node) {
    reaches[node] = true;

    for (int adj : adjLists[node]) {
      if (!reaches[adj]) {
        search1(adjLists, reaches, adj);
      }
    }
  }

  List<Integer> topologicalSort(List<Integer>[] adjLists) {
    int n = adjLists.length;

    List<Integer> sorted = new ArrayList<>();
    boolean[] visited = new boolean[n];
    for (int i = 0; i < n; ++i) {
      if (!visited[i]) {
        search(sorted, adjLists, visited, i);
      }
    }
    Collections.reverse(sorted);

    return sorted;
  }

  void search(List<Integer> sorted, List<Integer>[] adjLists, boolean[] visited, int node) {
    visited[node] = true;

    for (int adj : adjLists[node]) {
      if (!visited[adj]) {
        search(sorted, adjLists, visited, adj);
      }
    }

    sorted.add(node);
  }
}