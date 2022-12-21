import java.util.ArrayList;
import java.util.List;

class Solution {
  public boolean possibleBipartition(int n, int[][] dislikes) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] dislike : dislikes) {
      int person1 = dislike[0] - 1;
      int person2 = dislike[1] - 1;

      adjLists[person1].add(person2);
      adjLists[person2].add(person1);
    }

    int[] groups = new int[n];
    for (int i = 0; i < groups.length; ++i) {
      if (groups[i] == 0 && !search(adjLists, groups, i, 1)) {
        return false;
      }
    }

    return true;
  }

  boolean search(List<Integer>[] adjLists, int[] groups, int person, int expectedGroup) {
    if (groups[person] != 0) {
      return groups[person] == expectedGroup;
    }

    groups[person] = expectedGroup;

    for (int adj : adjLists[person]) {
      if (!search(adjLists, groups, adj, 3 - expectedGroup)) {
        return false;
      }
    }

    return true;
  }
}
