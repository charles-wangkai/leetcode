import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

class Solution {
  public boolean isPossible(int n, List<List<Integer>> edges) {
    @SuppressWarnings("unchecked")
    Set<Integer>[] adjSets = new Set[n];
    for (int i = 0; i < adjSets.length; ++i) {
      adjSets[i] = new HashSet<>();
    }
    for (List<Integer> edge : edges) {
      adjSets[edge.get(0) - 1].add(edge.get(1) - 1);
      adjSets[edge.get(1) - 1].add(edge.get(0) - 1);
    }

    int[] oddNodes =
        IntStream.range(0, adjSets.length).filter(i -> adjSets[i].size() % 2 != 0).toArray();

    return oddNodes.length == 0
        || (oddNodes.length == 2
            && (!adjSets[oddNodes[0]].contains(oddNodes[1])
                || IntStream.range(0, n)
                    .anyMatch(
                        i ->
                            i != oddNodes[0]
                                && i != oddNodes[1]
                                && !adjSets[i].contains(oddNodes[0])
                                && !adjSets[i].contains(oddNodes[1]))))
        || (oddNodes.length == 4
            && ((!adjSets[oddNodes[0]].contains(oddNodes[1])
                    && !adjSets[oddNodes[2]].contains(oddNodes[3]))
                || (!adjSets[oddNodes[0]].contains(oddNodes[2])
                    && !adjSets[oddNodes[1]].contains(oddNodes[3]))
                || (!adjSets[oddNodes[0]].contains(oddNodes[3])
                    && !adjSets[oddNodes[1]].contains(oddNodes[2]))));
  }
}
