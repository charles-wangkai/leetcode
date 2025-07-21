import java.util.ArrayList;
import java.util.List;

class Solution {
  static final int LIMIT = 5;

  public int[] popcountDepth(long[] nums, long[][] queries) {
    int n = nums.length;

    int[][] binaryIndexedTrees = new int[LIMIT + 1][Integer.highestOneBit(n) * 2 + 1];
    for (int i = 0; i < nums.length; ++i) {
      update(binaryIndexedTrees[computeDepth(nums[i])], i + 1, 1);
    }

    List<Integer> result = new ArrayList<>();
    for (long[] query : queries) {
      if (query[0] == 1) {
        int l = (int) query[1];
        int r = (int) query[2];
        int k = (int) query[3];

        result.add(query(binaryIndexedTrees[k], r + 1) - query(binaryIndexedTrees[k], l));
      } else {
        int idx = (int) query[1];
        long val = query[2];

        update(binaryIndexedTrees[computeDepth(nums[idx])], idx + 1, -1);
        nums[idx] = val;
        update(binaryIndexedTrees[computeDepth(nums[idx])], idx + 1, 1);
      }
    }

    return result.stream().mapToInt(Integer::intValue).toArray();
  }

  int computeDepth(long x) {
    return (x == 1) ? 0 : (1 + computeDepth(Long.bitCount(x)));
  }

  int query(int[] binaryIndexedTree, int index) {
    int result = 0;
    while (index != 0) {
      result += binaryIndexedTree[index];
      index -= index & -index;
    }

    return result;
  }

  void update(int[] binaryIndexedTree, int index, int delta) {
    while (index < binaryIndexedTree.length) {
      binaryIndexedTree[index] += delta;
      index += index & -index;
    }
  }
}