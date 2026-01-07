import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public int largestComponentSize(int[] nums) {
    Set<Integer> values = Arrays.stream(nums).boxed().collect(Collectors.toSet());

    int maxValue = Arrays.stream(nums).max().getAsInt();

    Dsu dsu = new Dsu(maxValue + 1);
    boolean[] primes = new boolean[dsu.parentOrSizes.length];
    Arrays.fill(primes, true);
    for (int i = 2; i < primes.length; ++i) {
      if (primes[i]) {
        int last = -1;
        for (int j = i; j < primes.length; j += i) {
          primes[j] = false;

          if (values.contains(j)) {
            if (last != -1) {
              dsu.union(j, last);
            }

            last = j;
          }
        }
      }
    }

    return dsu.buildLeaderToGroup().values().stream().mapToInt(List::size).max().getAsInt();
  }
}

class Dsu {
  int[] parentOrSizes;

  Dsu(int n) {
    parentOrSizes = new int[n];
    Arrays.fill(parentOrSizes, -1);
  }

  int find(int a) {
    if (parentOrSizes[a] < 0) {
      return a;
    }

    parentOrSizes[a] = find(parentOrSizes[a]);

    return parentOrSizes[a];
  }

  void union(int a, int b) {
    int aLeader = find(a);
    int bLeader = find(b);
    if (aLeader != bLeader) {
      parentOrSizes[aLeader] += parentOrSizes[bLeader];
      parentOrSizes[bLeader] = aLeader;
    }
  }

  int getSize(int a) {
    return -parentOrSizes[find(a)];
  }

  Map<Integer, List<Integer>> buildLeaderToGroup() {
    Map<Integer, List<Integer>> leaderToGroup = new HashMap<>();
    for (int i = 0; i < parentOrSizes.length; ++i) {
      int leader = find(i);
      leaderToGroup.putIfAbsent(leader, new ArrayList<>());
      leaderToGroup.get(leader).add(i);
    }

    return leaderToGroup;
  }
}
