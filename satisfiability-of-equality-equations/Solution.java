import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Solution {
  public boolean equationsPossible(String[] equations) {
    Character[] variables =
        Arrays.stream(equations)
            .flatMap(equation -> Stream.of(equation.charAt(0), equation.charAt(3)))
            .distinct()
            .toArray(Character[]::new);
    Map<Character, Integer> variableToIndex =
        IntStream.range(0, variables.length)
            .boxed()
            .collect(Collectors.toMap(i -> variables[i], i -> i));

    Dsu dsu = new Dsu(variables.length);
    for (String equation : equations) {
      if (equation.charAt(1) == '=') {
        char leftVariable = equation.charAt(0);
        char rightVariable = equation.charAt(3);

        dsu.union(variableToIndex.get(leftVariable), variableToIndex.get(rightVariable));
      }
    }

    return Arrays.stream(equations)
        .filter(equation -> equation.charAt(1) == '!')
        .allMatch(
            equation -> {
              char leftVariable = equation.charAt(0);
              char rightVariable = equation.charAt(3);

              return dsu.find(variableToIndex.get(leftVariable))
                  != dsu.find(variableToIndex.get(rightVariable));
            });
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
