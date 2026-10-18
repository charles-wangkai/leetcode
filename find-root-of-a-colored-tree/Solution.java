import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int findColoredRoot(int[][] edges, String colors) {
    int n = edges.length + 1;

    boolean[] hasParents = new boolean[n];
    int[] childCounts = new int[n];
    for (int[] edge : edges) {
      if ((colors.charAt(edge[0]) == 'R' && colors.charAt(edge[1]) == 'B')
          || (colors.charAt(edge[0]) == 'B' && colors.charAt(edge[1]) == 'W')
          || (colors.charAt(edge[0]) == 'W' && colors.charAt(edge[1]) == 'R')) {
        ++childCounts[edge[0]];
        hasParents[edge[1]] = true;
      } else if ((colors.charAt(edge[0]) == 'B' && colors.charAt(edge[1]) == 'R')
          || (colors.charAt(edge[0]) == 'W' && colors.charAt(edge[1]) == 'B')
          || (colors.charAt(edge[0]) == 'R' && colors.charAt(edge[1]) == 'W')) {
        ++childCounts[edge[1]];
        hasParents[edge[0]] = true;
      } else {
        return -1;
      }
    }

    int[] candidates = IntStream.range(0, hasParents.length).filter(i -> !hasParents[i]).toArray();

    return (Arrays.stream(childCounts).allMatch(childCount -> childCount <= 2)
            && candidates.length == 1)
        ? candidates[0]
        : -1;
  }
}