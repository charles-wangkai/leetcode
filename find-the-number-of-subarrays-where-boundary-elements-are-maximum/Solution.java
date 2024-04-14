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
    int[] binaryIndexedTree = new int[Integer.highestOneBit(nums.length) * 2 + 1];
    for (List<Integer> indices : valueToIndices.values()) {
      int length = -1;
      for (int i = 0; i < indices.size(); ++i) {
        update(binaryIndexedTree, indices.get(i) + 1, 1);

        if (i != 0
            && query(binaryIndexedTree, indices.get(i) + 1)
                    - query(binaryIndexedTree, indices.get(i - 1) + 1)
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