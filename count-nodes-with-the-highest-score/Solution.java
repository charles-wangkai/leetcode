import java.util.ArrayList;
import java.util.List;

class Solution {
  long maxScore;
  int nodeNumWithMaxScore;

  public int countHighestScoreNodes(int[] parents) {
    @SuppressWarnings("unchecked")
    List<Integer>[] childLists = new List[parents.length];
    for (int i = 0; i < childLists.length; ++i) {
      childLists[i] = new ArrayList<>();
    }
    for (int i = 1; i < parents.length; ++i) {
      childLists[parents[i]].add(i);
    }

    maxScore = 0;
    nodeNumWithMaxScore = 0;
    search(childLists, 0);

    return nodeNumWithMaxScore;
  }

  int search(List<Integer>[] childLists, int node) {
    long score = 1;
    int rest = childLists.length - 1;
    for (int child : childLists[node]) {
      int subtreeSize = search(childLists, child);
      score *= subtreeSize;
      rest -= subtreeSize;
    }
    if (rest != 0) {
      score *= rest;
    }

    if (score > maxScore) {
      maxScore = score;
      nodeNumWithMaxScore = 1;
    } else if (score == maxScore) {
      ++nodeNumWithMaxScore;
    }

    return childLists.length - rest;
  }
}
