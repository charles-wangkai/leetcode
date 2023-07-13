import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    int[] fromRests = new int[numCourses];

    @SuppressWarnings("unchecked")
    List<Integer>[] toLists = new List[numCourses];
    for (int i = 0; i < toLists.length; ++i) {
      toLists[i] = new ArrayList<>();
    }

    for (int[] prerequisite : prerequisites) {
      toLists[prerequisite[1]].add(prerequisite[0]);
      ++fromRests[prerequisite[0]];
    }

    boolean[] takens = new boolean[numCourses];
    for (int i = 0; i < numCourses; ++i) {
      if (!takens[i] && fromRests[i] == 0) {
        take(fromRests, toLists, takens, i);
      }
    }

    return IntStream.range(0, takens.length).allMatch(i -> takens[i]);
  }

  void take(int[] fromRests, List<Integer>[] toLists, boolean[] takens, int node) {
    takens[node] = true;

    for (int to : toLists[node]) {
      --fromRests[to];

      if (!takens[to] && fromRests[to] == 0) {
        take(fromRests, toLists, takens, to);
      }
    }
  }
}
