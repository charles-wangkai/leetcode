class Solution {
  static final int LIMIT = 100005;

  public long countMajoritySubarrays(int[] nums, int target) {
    int[] binaryIndexedTree = new int[LIMIT * 2 + 1];
    update(binaryIndexedTree, getIndex(0), 1);

    long result = 0;
    int diff = 0;
    for (int num : nums) {
      diff += (num == target) ? 1 : -1;
      result += query(binaryIndexedTree, getIndex(diff - 1));
      update(binaryIndexedTree, getIndex(diff), 1);
    }

    return result;
  }

  int getIndex(int diff) {
    return diff + LIMIT;
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