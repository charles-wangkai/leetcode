class Solution {
  public int numberOfNodes(int n, int[] queries) {
    boolean[] flipped = new boolean[n];
    for (int query : queries) {
      flipped[query - 1] = !flipped[query - 1];
    }

    return search(flipped, 0, 0);
  }

  int search(boolean[] flipped, int index, int parentValue) {
    if (index >= flipped.length) {
      return 0;
    }

    int value = flipped[index] ? (1 - parentValue) : parentValue;

    return ((value == 1) ? 1 : 0)
        + search(flipped, index * 2 + 1, value)
        + search(flipped, index * 2 + 2, value);
  }
}
