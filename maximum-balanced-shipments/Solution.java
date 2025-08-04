class Solution {
  public int maxBalancedShipments(int[] weight) {
    int result = 0;
    int maxWeight = Integer.MIN_VALUE;
    for (int w : weight) {
      maxWeight = Math.max(maxWeight, w);
      if (w < maxWeight) {
        ++result;
        maxWeight = Integer.MIN_VALUE;
      }
    }

    return result;
  }
}