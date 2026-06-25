import java.util.ArrayList;
import java.util.List;

class Solution {
  public long finishTime(int n, int[][] edges, int[] baseTime) {
    @SuppressWarnings("unchecked")
    List<Integer>[] childLists = new List[n];
    for (int i = 0; i < childLists.length; ++i) {
      childLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      childLists[edge[0]].add(edge[1]);
    }

    long[] finishTimes = new long[n];
    search(finishTimes, childLists, baseTime, 0);

    return finishTimes[0];
  }

  void search(long[] finishTimes, List<Integer>[] childLists, int[] baseTime, int node) {
    if (childLists[node].isEmpty()) {
      finishTimes[node] = baseTime[node];
    } else {
      long earliest = Long.MAX_VALUE;
      long latest = Long.MIN_VALUE;
      for (int child : childLists[node]) {
        search(finishTimes, childLists, baseTime, child);

        earliest = Math.min(earliest, finishTimes[child]);
        latest = Math.max(latest, finishTimes[child]);
      }

      long ownDuration = (latest - earliest) + baseTime[node];
      finishTimes[node] = latest + ownDuration;
    }
  }
}