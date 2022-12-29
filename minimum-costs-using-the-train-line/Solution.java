class Solution {
  public long[] minimumCosts(int[] regular, int[] express, int expressCost) {
    long[] result = new long[regular.length];
    long regularCostSum = 0;
    long expressCostSum = expressCost;
    for (int i = 0; i < result.length; ++i) {
      long nextRegularCostSum = Math.min(regularCostSum + regular[i], expressCostSum + express[i]);
      long nextExpressCostSum =
          Math.min(regularCostSum + regular[i] + expressCost, expressCostSum + express[i]);

      result[i] = Math.min(nextRegularCostSum, nextExpressCostSum);

      regularCostSum = nextRegularCostSum;
      expressCostSum = nextExpressCostSum;
    }

    return result;
  }
}
