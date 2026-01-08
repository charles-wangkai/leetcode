import java.util.ArrayList;
import java.util.List;

class Solution {
  static final int LIMIT = 5;

  public int[] popcountDepth(long[] nums, long[][] queries) {
    int n = nums.length;

    FenwickTree[] fenwickTrees = new FenwickTree[LIMIT + 1];
    for (int i = 0; i < fenwickTrees.length; ++i) {
      fenwickTrees[i] = new FenwickTree(n);
    }
    for (int i = 0; i < nums.length; ++i) {
      fenwickTrees[computeDepth(nums[i])].add(i + 1, 1);
    }

    List<Integer> result = new ArrayList<>();
    for (long[] query : queries) {
      if (query[0] == 1) {
        int l = (int) query[1];
        int r = (int) query[2];
        int k = (int) query[3];

        result.add(fenwickTrees[k].computePrefixSum(r + 1) - fenwickTrees[k].computePrefixSum(l));
      } else {
        int idx = (int) query[1];
        long val = query[2];

        fenwickTrees[computeDepth(nums[idx])].add(idx + 1, -1);
        nums[idx] = val;
        fenwickTrees[computeDepth(nums[idx])].add(idx + 1, 1);
      }
    }

    return result.stream().mapToInt(Integer::intValue).toArray();
  }

  int computeDepth(long x) {
    return (x == 1) ? 0 : (1 + computeDepth(Long.bitCount(x)));
  }
}

class FenwickTree {
  int[] a;

  FenwickTree(int size) {
    a = new int[Integer.highestOneBit(size) * 2 + 1];
  }

  void add(int pos, int delta) {
    while (pos < a.length) {
      a[pos] += delta;
      pos += pos & -pos;
    }
  }

  int computePrefixSum(int pos) {
    int result = 0;
    while (pos != 0) {
      result += a[pos];
      pos -= pos & -pos;
    }

    return result;
  }
}
