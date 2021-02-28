class Solution {
  Integer closest;

  public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
    closest = null;
    search(baseCosts, toppingCosts, target, 0, 0);

    return closest;
  }

  void search(
      int[] baseCosts, int[] toppingCosts, int target, int toppingIndex, int toppingCostTotal) {
    if (toppingIndex == toppingCosts.length) {
      for (int baseCost : baseCosts) {
        int total = baseCost + toppingCostTotal;
        if (closest == null
            || Math.abs(total - target) < Math.abs(closest - target)
            || (Math.abs(total - target) == Math.abs(closest - target) && total < closest)) {
          closest = total;
        }
      }

      return;
    }

    for (int i = 0; i <= 2; ++i) {
      search(
          baseCosts,
          toppingCosts,
          target,
          toppingIndex + 1,
          toppingCostTotal + toppingCosts[toppingIndex] * i);
    }
  }
}
