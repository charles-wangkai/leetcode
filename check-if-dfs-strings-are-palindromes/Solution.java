import java.util.ArrayList;
import java.util.List;

class Solution {
  static final int BASE = 31;
  static final int MODULUS = 1_000_000_007;

  public boolean[] findAnswer(int[] parent, String s) {
    int n = parent.length;

    @SuppressWarnings("unchecked")
    List<Integer>[] childrenList = new List[n];
    for (int i = 0; i < childrenList.length; ++i) {
      childrenList[i] = new ArrayList<>();
    }
    for (int i = 1; i < parent.length; ++i) {
      childrenList[parent[i]].add(i);
    }

    StringBuilder dfsStr = new StringBuilder();
    int[] beginIndices = new int[n];
    int[] endIndices = new int[n];
    search(s, childrenList, beginIndices, endIndices, dfsStr, 0);

    int[] prefixHashes = buildPrefixHashes(dfsStr.toString());
    int[] reversedPrefixHashes = buildPrefixHashes(dfsStr.reverse().toString());

    int[] basePowers = buildBasePowers(n);
    boolean[] result = new boolean[n];
    for (int i = 0; i < result.length; ++i) {
      result[i] =
          computeRangeHash(basePowers, prefixHashes, beginIndices[i], endIndices[i])
              == computeRangeHash(
                  basePowers, reversedPrefixHashes, n - 1 - endIndices[i], n - 1 - beginIndices[i]);
    }

    return result;
  }

  int[] buildBasePowers(int n) {
    int[] result = new int[n + 1];
    result[0] = 1;
    for (int i = 1; i < result.length; ++i) {
      result[i] = multiplyMod(result[i - 1], BASE);
    }

    return result;
  }

  int computeRangeHash(int[] basePowers, int[] prefixHashes, int beginIndex, int endIndex) {
    return addMod(
        prefixHashes[endIndex + 1],
        -multiplyMod(prefixHashes[beginIndex], basePowers[endIndex - beginIndex + 1]));
  }

  int[] buildPrefixHashes(String str) {
    int[] result = new int[str.length() + 1];
    for (int i = 1; i < result.length; ++i) {
      result[i] = addMod(multiplyMod(result[i - 1], BASE), str.charAt(i - 1) - 'a');
    }

    return result;
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }

  void search(
      String s,
      List<Integer>[] childrenList,
      int[] beginIndices,
      int[] endIndices,
      StringBuilder dfsStr,
      int node) {
    beginIndices[node] = dfsStr.length();

    for (int child : childrenList[node]) {
      search(s, childrenList, beginIndices, endIndices, dfsStr, child);
    }

    endIndices[node] = dfsStr.length();
    dfsStr.append(s.charAt(node));
  }
}