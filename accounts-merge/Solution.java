import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Solution {
  public List<List<String>> accountsMerge(List<List<String>> accounts) {
    Map<String, String> emailToName = new HashMap<>();
    for (List<String> account : accounts) {
      for (int i = 1; i < account.size(); ++i) {
        emailToName.put(account.get(i), account.get(0));
      }
    }

    String[] emails = emailToName.keySet().stream().toArray(String[]::new);
    Map<String, Integer> emailToIndex =
        IntStream.range(0, emails.length).boxed().collect(Collectors.toMap(i -> emails[i], i -> i));

    Dsu dsu = new Dsu(emails.length);

    for (List<String> account : accounts) {
      for (int i = 1; i + 1 < account.size(); ++i) {
        dsu.union(emailToIndex.get(account.get(i)), emailToIndex.get(account.get(i + 1)));
      }
    }

    Map<Integer, List<Integer>> leaderToGroup = dsu.buildLeaderToGroup();

    return leaderToGroup.keySet().stream()
        .map(
            leader ->
                Stream.concat(
                        Stream.of(emailToName.get(emails[leader])),
                        leaderToGroup.get(leader).stream().map(index -> emails[index]).sorted())
                    .toList())
        .toList();
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
