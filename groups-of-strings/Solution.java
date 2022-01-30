import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int ALPHABET_SIZE = 26;

  public int[] groupStrings(String[] words) {
    Map<Integer, Integer> keyToCount = new HashMap<>();
    Map<Integer, Integer> keyToParent = new HashMap<>();
    for (String word : words) {
      int key = buildKey(word);

      keyToCount.put(key, keyToCount.getOrDefault(key, 0) + 1);
      keyToParent.put(key, -1);
    }

    for (int key : keyToParent.keySet()) {
      for (int i = 0; i < ALPHABET_SIZE; ++i) {
        union(keyToParent, key, key | (1 << i));
      }

      for (int i = 0; i < ALPHABET_SIZE; ++i) {
        if ((key & (1 << i)) != 0) {
          union(keyToParent, key, key - (1 << i));
        }
      }

      for (int i = 0; i < ALPHABET_SIZE; ++i) {
        if ((key & (1 << i)) != 0) {
          for (int j = 0; j < ALPHABET_SIZE; ++j) {
            union(keyToParent, key, (key - (1 << i)) | (1 << j));
          }
        }
      }
    }

    Map<Integer, Integer> rootToCount = new HashMap<>();
    for (int key : keyToParent.keySet()) {
      int root = findRoot(keyToParent, key);
      rootToCount.put(root, rootToCount.getOrDefault(root, 0) + keyToCount.get(key));
    }

    return new int[] {
      rootToCount.size(), rootToCount.values().stream().mapToInt(x -> x).max().getAsInt()
    };
  }

  static void union(Map<Integer, Integer> keyToParent, int key1, int key2) {
    if (keyToParent.containsKey(key2)) {
      int root1 = findRoot(keyToParent, key1);
      int root2 = findRoot(keyToParent, key2);
      if (root1 != root2) {
        keyToParent.put(root2, root1);
      }
    }
  }

  static int findRoot(Map<Integer, Integer> keyToParent, int key) {
    int root = key;
    while (keyToParent.get(root) != -1) {
      root = keyToParent.get(root);
    }

    int p = key;
    while (p != root) {
      int next = keyToParent.get(p);
      keyToParent.put(p, root);

      p = next;
    }

    return root;
  }

  int buildKey(String word) {
    return word.chars().map(c -> 1 << (c - 'a')).reduce(0, (x, y) -> x | y);
  }
}