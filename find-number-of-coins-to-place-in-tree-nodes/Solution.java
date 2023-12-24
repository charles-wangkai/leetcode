import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public long[] placedCoins(int[][] edges, int[] cost) {
    int n = cost.length;

    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    long[] coins = new long[n];
    search(adjLists, cost, coins, -1, 0);

    return coins;
  }

  Element search(List<Integer>[] adjLists, int[] cost, long[] coins, int parent, int node) {
    Element result =
        new Element(
            cost[node], Integer.MIN_VALUE, Integer.MIN_VALUE, cost[node], Integer.MAX_VALUE);
    for (int adj : adjLists[node]) {
      if (adj != parent) {
        result = merge(result, search(adjLists, cost, coins, node, adj));
      }
    }

    coins[node] =
        (result.max3() == Integer.MIN_VALUE)
            ? 1
            : Math.max(
                0,
                Math.max(
                    (long) result.max1() * result.max2() * result.max3(),
                    (long) result.max1() * result.min1() * result.min2()));

    return result;
  }

  Element merge(Element e1, Element e2) {
    int[] maxs =
        IntStream.of(e1.max1(), e1.max2(), e1.max3(), e2.max1(), e2.max2(), e2.max3())
            .boxed()
            .sorted(Comparator.reverseOrder())
            .mapToInt(Integer::intValue)
            .limit(3)
            .toArray();
    int[] mins =
        IntStream.of(e1.min1(), e1.min2(), e2.min1(), e2.min2()).sorted().limit(2).toArray();

    return new Element(maxs[0], maxs[1], maxs[2], mins[0], mins[1]);
  }
}

record Element(int max1, int max2, int max3, int min1, int min2) {}
