import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    Arrays.sort(candidates);

    List<List<Integer>> solutions = new ArrayList<>();
    search(solutions, candidates, target, new ArrayList<>(), 0);

    return solutions;
  }

  void search(
      List<List<Integer>> solutions,
      int[] candidates,
      int rest,
      List<Integer> chosenIndices,
      int index) {
    if (rest == 0) {
      solutions.add(chosenIndices.stream().map(chosenIndex -> candidates[chosenIndex]).toList());

      return;
    }
    if (index == candidates.length || candidates[index] > rest) {
      return;
    }

    search(solutions, candidates, rest, chosenIndices, index + 1);

    if (!(index != 0
        && candidates[index] == candidates[index - 1]
        && (chosenIndices.isEmpty() || chosenIndices.get(chosenIndices.size() - 1) != index - 1))) {
      chosenIndices.add(index);
      search(solutions, candidates, rest - candidates[index], chosenIndices, index + 1);
      chosenIndices.remove(chosenIndices.size() - 1);
    }
  }
}
