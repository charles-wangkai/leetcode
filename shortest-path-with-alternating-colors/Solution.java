import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

class Solution {
  public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
    List<Integer>[] redAdjLists = buildAdjLists(n, redEdges);
    List<Integer>[] blueAdjLists = buildAdjLists(n, blueEdges);

    int[] redMinDistances = new int[n];
    Arrays.fill(redMinDistances, Integer.MAX_VALUE);
    redMinDistances[0] = 0;
    int[] blueMinDistances = new int[n];
    Arrays.fill(blueMinDistances, Integer.MAX_VALUE);
    blueMinDistances[0] = 0;

    Queue<Element> queue = new ArrayDeque<>();
    queue.offer(new Element(0, true));
    queue.offer(new Element(0, false));

    while (!queue.isEmpty()) {
      Element head = queue.poll();

      if (head.redOrBlue()) {
        for (int blueAdj : blueAdjLists[head.node()]) {
          if (blueMinDistances[blueAdj] == Integer.MAX_VALUE) {
            blueMinDistances[blueAdj] = redMinDistances[head.node()] + 1;
            queue.offer(new Element(blueAdj, false));
          }
        }
      } else {
        for (int redAdj : redAdjLists[head.node()]) {
          if (redMinDistances[redAdj] == Integer.MAX_VALUE) {
            redMinDistances[redAdj] = blueMinDistances[head.node()] + 1;
            queue.offer(new Element(redAdj, true));
          }
        }
      }
    }

    return IntStream.range(0, n)
        .map(i -> Math.min(redMinDistances[i], blueMinDistances[i]))
        .map(x -> (x == Integer.MAX_VALUE) ? -1 : x)
        .toArray();
  }

  List<Integer>[] buildAdjLists(int n, int[][] edges) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
    }

    return adjLists;
  }
}

record Element(int node, boolean redOrBlue) {}
