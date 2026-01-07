import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  static final int ALPHABET_SIZE = 26;

  public int[] groupStrings(String[] words) {
    int[] keys = Arrays.stream(words).mapToInt(this::buildKey).distinct().toArray();
    Map<Integer, Integer> keyToIndex =
        IntStream.range(0, keys.length).boxed().collect(Collectors.toMap(i -> keys[i], i -> i));

    int[] counts = new int[keys.length];
    for (String word : words) {
      ++counts[keyToIndex.get(buildKey(word))];
    }

    Dsu dsu = new Dsu(keys.length);
    for (int key : keys) {
      for (int i = 0; i < ALPHABET_SIZE; ++i) {
        union(dsu, keyToIndex, key, key | (1 << i));
      }

      for (int i = 0; i < ALPHABET_SIZE; ++i) {
        if (((key >> i) & 1) == 1) {
          union(dsu, keyToIndex, key, key - (1 << i));
        }
      }

      for (int i = 0; i < ALPHABET_SIZE; ++i) {
        if (((key >> i) & 1) == 1) {
          for (int j = 0; j < ALPHABET_SIZE; ++j) {
            union(dsu, keyToIndex, key, (key - (1 << i)) | (1 << j));
          }
        }
      }
    }

    Map<Integer, List<Integer>> leaderToGroup = dsu.buildLeaderToGroup();

    return new int[] {
      leaderToGroup.size(),
      leaderToGroup.values().stream()
          .mapToInt(group -> group.stream().mapToInt(node -> counts[node]).sum())
          .max()
          .getAsInt()
    };
  }

  void union(Dsu dsu, Map<Integer, Integer> keyToIndex, int key1, int key2) {
    if (keyToIndex.containsKey(key2)) {
      dsu.union(keyToIndex.get(key1), keyToIndex.get(key2));
    }
  }

  int buildKey(String word) {
    return word.chars().map(c -> 1 << (c - 'a')).reduce(0, (x, y) -> x | y);
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
