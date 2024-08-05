import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public int[] timeTaken(int[][] edges) {
    int n = edges.length + 1;

    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    int[] subtreeTimes = new int[n];
    search1(subtreeTimes, adjLists, -1, 0);

    int[] times = new int[n];
    search2(times, adjLists, subtreeTimes, 0, -1, 0);

    return times;
  }

  void search2(
      int[] times,
      List<Integer>[] adjLists,
      int[] subtreeTimes,
      int parentTime,
      int parent,
      int node) {
    times[node] = Math.max(parentTime, subtreeTimes[node]);

    SortedMap<Integer, Integer> timeToCount = new TreeMap<>();
    updateMap(timeToCount, parentTime, 1);

    for (int adj : adjLists[node]) {
      if (adj != parent) {
        updateMap(timeToCount, ((adj % 2 == 0) ? 2 : 1) + subtreeTimes[adj], 1);
      }
    }

    for (int adj : adjLists[node]) {
      if (adj != parent) {
        int time = ((adj % 2 == 0) ? 2 : 1) + subtreeTimes[adj];
        updateMap(timeToCount, time, -1);

        search2(
            times,
            adjLists,
            subtreeTimes,
            ((node % 2 == 0) ? 2 : 1) + timeToCount.lastKey(),
            node,
            adj);

        updateMap(timeToCount, time, 1);
      }
    }
  }

  void updateMap(SortedMap<Integer, Integer> timeToCount, int time, int delta) {
    timeToCount.put(time, timeToCount.getOrDefault(time, 0) + delta);
    timeToCount.remove(time, 0);
  }

  void search1(int[] subtreeTimes, List<Integer>[] adjLists, int parent, int node) {
    subtreeTimes[node] = 0;
    for (int adj : adjLists[node]) {
      if (adj != parent) {
        search1(subtreeTimes, adjLists, node, adj);
        subtreeTimes[node] =
            Math.max(subtreeTimes[node], ((adj % 2 == 0) ? 2 : 1) + subtreeTimes[adj]);
      }
    }
  }
}