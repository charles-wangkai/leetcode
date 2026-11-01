// https://leetcode.com/problems/finish-time-of-tasks-ii/solutions/8348068/cursed-re-root-dp-by-leetgoat_dot_io-dqi1/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public long finishTime(int n, int[][] edges, int[] baseTime) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    long[] down = new long[n];
    int[] downLight = new int[n];
    int[] downHeavy = new int[n];
    Integer[] downLight2 = new Integer[n];
    Integer[] downHeavy2 = new Integer[n];
    long[] earlyBelow = IntStream.range(0, n).mapToLong(i -> Long.MAX_VALUE).toArray();
    long[] lateBelow = IntStream.range(0, n).mapToLong(i -> Long.MIN_VALUE).toArray();
    search1(
        baseTime,
        adjLists,
        down,
        downLight,
        downHeavy,
        downLight2,
        downHeavy2,
        earlyBelow,
        lateBelow,
        -1,
        0);

    Long[] up = new Long[n];

    long[] rootFinishTimes = new long[n];
    rootFinishTimes[0] = down[0];

    search2(
        baseTime,
        adjLists,
        down,
        downLight,
        downHeavy,
        downLight2,
        downHeavy2,
        earlyBelow,
        lateBelow,
        up,
        rootFinishTimes,
        -1,
        0);

    return Arrays.stream(rootFinishTimes).min().getAsLong();
  }

  void search2(
      int[] baseTime,
      List<Integer>[] adjLists,
      long[] down,
      int[] downLight,
      int[] downHeavy,
      Integer[] downLight2,
      Integer[] downHeavy2,
      long[] earlyBelow,
      long[] lateBelow,
      Long[] up,
      long[] rootFinishTimes,
      int parent,
      int node) {
    for (int adj : adjLists[node]) {
      if (adj != parent) {
        long childEarlyDown = earlyBelow[adj];
        long childLateDown = lateBelow[adj];

        long upDownLow =
            (downLight[node] == adj)
                ? ((downLight2[node] == null) ? Long.MAX_VALUE : down[downLight2[node]])
                : down[downLight[node]];

        long upDownHigh =
            (downHeavy[node] == adj)
                ? ((downHeavy2[node] == null) ? Long.MIN_VALUE : down[downHeavy2[node]])
                : down[downHeavy[node]];

        long nodesMin = Math.min(upDownLow, (up[node] == null) ? Long.MAX_VALUE : up[node]);
        long nodesMax = Math.max(upDownHigh, (up[node] == null) ? Long.MIN_VALUE : up[node]);

        up[adj] =
            (nodesMax == Long.MIN_VALUE)
                ? (long) baseTime[node]
                : (nodesMax - nodesMin + baseTime[node] + nodesMax);

        long minAdj = Math.min(childEarlyDown, up[adj]);
        long maxAdj = Math.max(childLateDown, up[adj]);
        long ownD = maxAdj - minAdj + baseTime[adj];
        rootFinishTimes[adj] = ownD + maxAdj;

        search2(
            baseTime,
            adjLists,
            down,
            downLight,
            downHeavy,
            downLight2,
            downHeavy2,
            earlyBelow,
            lateBelow,
            up,
            rootFinishTimes,
            node,
            adj);
      }
    }
  }

  void search1(
      int[] baseTime,
      List<Integer>[] adjLists,
      long[] down,
      int[] downLight,
      int[] downHeavy,
      Integer[] downLight2,
      Integer[] downHeavy2,
      long[] earlyBelow,
      long[] lateBelow,
      int parent,
      int node) {
    List<Integer> children = new ArrayList<>();
    for (int adj : adjLists[node]) {
      if (adj != parent) {
        search1(
            baseTime,
            adjLists,
            down,
            downLight,
            downHeavy,
            downLight2,
            downHeavy2,
            earlyBelow,
            lateBelow,
            node,
            adj);

        children.add(adj);
      }
    }

    if (children.isEmpty()) {
      down[node] = baseTime[node];
    } else {
      Collections.sort(children, Comparator.comparing(child -> down[child]));

      long max = down[children.getLast()];
      long min = down[children.getFirst()];

      long ownD = max - min + baseTime[node];
      down[node] = ownD + max;

      downLight[node] = children.getFirst();
      downHeavy[node] = children.getLast();

      if (children.size() > 1) {
        downLight2[node] = children.get(1);
        downHeavy2[node] = children.get(children.size() - 2);
      }

      earlyBelow[node] = min;
      lateBelow[node] = max;
    }
  }
}
