import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Solution {
  int maxLength;

  public int longestPath(int[] parent, String s) {
    @SuppressWarnings("unchecked")
    List<Integer>[] childLists = new List[parent.length];
    for (int i = 0; i < childLists.length; ++i) {
      childLists[i] = new ArrayList<>();
    }
    for (int i = 1; i < parent.length; ++i) {
      childLists[parent[i]].add(i);
    }

    maxLength = 0;
    search(s, childLists, 0);

    return maxLength;
  }

  int search(String s, List<Integer>[] childLists, int node) {
    List<Integer> candidateSubLengths = new ArrayList<>();
    for (int child : childLists[node]) {
      int subLength = search(s, childLists, child);
      if (s.charAt(child) != s.charAt(node)) {
        candidateSubLengths.add(subLength);
      }
    }
    Collections.sort(candidateSubLengths, Comparator.reverseOrder());

    if (candidateSubLengths.isEmpty()) {
      maxLength = Math.max(maxLength, 1);
    } else if (candidateSubLengths.size() == 1) {
      maxLength = Math.max(maxLength, 1 + candidateSubLengths.get(0));
    } else {
      maxLength = Math.max(maxLength, 1 + candidateSubLengths.get(0) + candidateSubLengths.get(1));
    }

    return 1 + (candidateSubLengths.isEmpty() ? 0 : candidateSubLengths.get(0));
  }
}
