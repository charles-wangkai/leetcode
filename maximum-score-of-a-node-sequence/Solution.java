import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Solution {
  public int maximumScore(int[] scores, int[][] edges) {
    int n = scores.length;

    @SuppressWarnings("unchecked")
    List<Integer>[] candidateLists = new List[n];
    for (int i = 0; i < candidateLists.length; ++i) {
      candidateLists[i] = new ArrayList<>();
    }

    for (int[] edge : edges) {
      updateCandidateList(scores, candidateLists[edge[0]], edge[1]);
      updateCandidateList(scores, candidateLists[edge[1]], edge[0]);
    }

    int result = -1;
    for (int[] edge : edges) {
      for (int candidate1 : candidateLists[edge[0]]) {
        for (int candidate2 : candidateLists[edge[1]]) {
          if (candidate1 != edge[0]
              && candidate1 != edge[1]
              && candidate2 != edge[0]
              && candidate2 != edge[1]
              && candidate2 != candidate1) {
            result =
                Math.max(
                    result,
                    scores[edge[0]] + scores[edge[1]] + scores[candidate1] + scores[candidate2]);
          }
        }
      }
    }

    return result;
  }

  void updateCandidateList(int[] scores, List<Integer> candidateList, int node) {
    candidateList.add(node);
    candidateList.sort(Comparator.comparing((Integer i) -> scores[i]).reversed());

    if (candidateList.size() == 4) {
      candidateList.remove(candidateList.size() - 1);
    }
  }
}