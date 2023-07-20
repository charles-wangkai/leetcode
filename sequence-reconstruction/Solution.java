import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
  public boolean sequenceReconstruction(int[] org, int[][] seqs) {
    Set<Integer> seqSet = new HashSet<>();
    for (int[] seq : seqs) {
      for (int n : seq) {
        if (!(n >= 1 && n <= org.length)) {
          return false;
        }

        seqSet.add(n);
      }
    }
    if (seqSet.size() < org.length) {
      return false;
    }

    @SuppressWarnings("unchecked")
    Set<Integer>[] predSets = new Set[org.length];
    for (int i = 0; i < predSets.length; i++) {
      predSets[i] = new HashSet<>();
    }

    @SuppressWarnings("unchecked")
    Set<Integer>[] succSets = new Set[org.length];
    for (int i = 0; i < succSets.length; i++) {
      succSets[i] = new HashSet<>();
    }

    for (int[] seq : seqs) {
      for (int i = 0; i < seq.length - 1; i++) {
        predSets[seq[i + 1] - 1].add(seq[i] - 1);
        succSets[seq[i] - 1].add(seq[i + 1] - 1);
      }
    }

    boolean[] visited = new boolean[org.length];

    List<Integer> nexts = new ArrayList<>();
    for (int i = 0; i < predSets.length; i++) {
      if (predSets[i].isEmpty()) {
        nexts.add(i);
      }
    }

    for (int i = 0; i < org.length; i++) {
      if (nexts.size() != 1) {
        return false;
      }

      int next = nexts.get(0);
      visited[next] = true;

      List<Integer> updatedNexts = new ArrayList<>();
      for (int succ : succSets[next]) {
        predSets[succ].remove(next);

        if (!visited[succ] && predSets[succ].isEmpty()) {
          updatedNexts.add(succ);
        }
      }

      nexts = updatedNexts;
    }
    return true;
  }
}
