import java.util.ArrayList;
import java.util.List;

public class Solution {
  public List<Integer> cheapestJump(int[] A, int B) {
    int length = A.length;

    int[] costs = new int[length];
    @SuppressWarnings("unchecked")
    List<Integer>[] paths = new List[length];

    costs[0] = A[0];
    paths[0] = new ArrayList<>();
    paths[0].add(1);

    for (int i = 1; i < length; i++) {
      costs[i] = -1;
      paths[i] = new ArrayList<>();

      if (A[i] >= 0) {
        for (int j = 1; j <= B && j <= i; j++) {
          if (costs[i - j] >= 0
              && (costs[i] < 0
                  || costs[i - j] + A[i] < costs[i]
                  || (costs[i - j] + A[i] == costs[i]
                      && isLess(concat(paths[i - j], i + 1), paths[i])))) {
            costs[i] = costs[i - j] + A[i];
            paths[i] = concat(paths[i - j], i + 1);
          }
        }
      }
    }

    return paths[length - 1];
  }

  List<Integer> concat(List<Integer> path, int sequence) {
    List<Integer> result = new ArrayList<>(path);
    result.add(sequence);
    return result;
  }

  boolean isLess(List<Integer> path1, List<Integer> path2) {
    for (int i = 0; ; i++) {
      if (i < path1.size()) {
        if (i < path2.size()) {
          if (!path1.get(i).equals(path2.get(i))) {
            return path1.get(i) < path2.get(i);
          }
        } else {
          return false;
        }
      } else {
        return true;
      }
    }
  }
}
