import java.util.stream.IntStream;

class Solution {
  static final int SIZE = 1 << 13;

  public int maxProfit(int[] prices, int[] profits) {
    int[] leftMaxProfits = new int[prices.length];
    int[] leftBinaryIndexedTree = new int[SIZE];
    for (int i = 0; i < leftMaxProfits.length; ++i) {
      leftMaxProfits[i] = query(leftBinaryIndexedTree, prices[i] - 1);
      update(leftBinaryIndexedTree, prices[i], profits[i]);
    }

    int[] rightMaxProfits = new int[prices.length];
    int[] rightBinaryIndexedTree = new int[SIZE];
    for (int i = rightMaxProfits.length - 1; i >= 0; --i) {
      rightMaxProfits[i] = query(rightBinaryIndexedTree, (SIZE - prices[i]) - 1);
      update(rightBinaryIndexedTree, SIZE - prices[i], profits[i]);
    }

    return IntStream.range(0, prices.length)
        .filter(i -> leftMaxProfits[i] != 0 && rightMaxProfits[i] != 0)
        .map(i -> leftMaxProfits[i] + profits[i] + rightMaxProfits[i])
        .max()
        .orElse(-1);
  }

  int query(int[] binaryIndexedTree, int i) {
    int result = 0;
    while (i != 0) {
      result = Math.max(result, binaryIndexedTree[i]);
      i -= i & -i;
    }

    return result;
  }

  void update(int[] binaryIndexedTree, int i, int x) {
    while (i < binaryIndexedTree.length) {
      binaryIndexedTree[i] = Math.max(binaryIndexedTree[i], x);
      i += i & -i;
    }
  }
}
