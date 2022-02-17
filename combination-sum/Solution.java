import java.util.ArrayList;
import java.util.List;

class Solution {
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> combinations = new ArrayList<>();
    search(combinations, candidates, target, new ArrayList<>(), 0);

    return combinations;
  }

  void search(
      List<List<Integer>> combinations,
      int[] candidates,
      int rest,
      List<Integer> combination,
      int index) {
    if (rest == 0) {
      combinations.add(List.copyOf(combination));

      return;
    }
    if (index == candidates.length) {
      return;
    }

    search(combinations, candidates, rest, combination, index + 1);

    if (candidates[index] <= rest) {
      combination.add(candidates[index]);
      search(combinations, candidates, rest - candidates[index], combination, index);
      combination.remove(combination.size() - 1);
    }
  }
}
