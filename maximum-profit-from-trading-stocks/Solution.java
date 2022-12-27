import java.util.HashMap;
import java.util.Map;

class Solution {
  public int maximumProfit(int[] present, int[] future, int budget) {
    Map<Integer, Integer> budgetToProfit = Map.of(budget, 0);
    for (int i = 0; i < present.length; ++i) {
      Map<Integer, Integer> nextBudgetToProfit = new HashMap<>(budgetToProfit);
      for (int b : budgetToProfit.keySet()) {
        if (b >= present[i] && future[i] > present[i]) {
          nextBudgetToProfit.put(
              b - present[i],
              Math.max(
                  nextBudgetToProfit.getOrDefault(b - present[i], 0),
                  budgetToProfit.get(b) + future[i] - present[i]));
        }
      }

      budgetToProfit = nextBudgetToProfit;
    }

    return budgetToProfit.values().stream().mapToInt(x -> x).max().getAsInt();
  }
}
