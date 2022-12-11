import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int maxStarSum(int[] vals, int[][] edges, int k) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[vals.length];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    return IntStream.range(0, vals.length)
        .map(
            i ->
                vals[i]
                    + adjLists[i].stream()
                        .map(adj -> vals[adj])
                        .filter(x -> x >= 0)
                        .sorted(Comparator.reverseOrder())
                        .limit(k)
                        .mapToInt(x -> x)
                        .sum())
        .max()
        .getAsInt();
  }
}
