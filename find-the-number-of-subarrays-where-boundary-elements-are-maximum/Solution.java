import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public long numberOfSubarrays(int[] nums) {
    SortedMap<Integer, List<Integer>> valueToIndices = new TreeMap<>();
    for (int i = 0; i < nums.length; ++i) {
      valueToIndices.putIfAbsent(nums[i], new ArrayList<>());
      valueToIndices.get(nums[i]).add(i);
    }

    long result = 0;
    FenwickTree fenwickTree = new FenwickTree(nums.length);
    for (List<Integer> indices : valueToIndices.values()) {
      int length = -1;
      for (int i = 0; i < indices.size(); ++i) {
        fenwickTree.add(indices.get(i) + 1, 1);

        if (i != 0
            && fenwickTree.computePrefixSum(indices.get(i) + 1)
                    - fenwickTree.computePrefixSum(indices.get(i - 1) + 1)
                == indices.get(i) - indices.get(i - 1)) {
          ++length;
        } else {
          length = 1;
        }

        result += length;
      }
    }

    return result;
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
