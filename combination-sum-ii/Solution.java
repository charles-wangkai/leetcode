import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
  public List<List<Integer>> combinationSum2(int[] num, int target) {
    Arrays.sort(num);
    List<List<Integer>> solutions = new ArrayList<>();
    search(solutions, num, target, new ArrayList<>(), 0);
    return solutions;
  }

  void search(
      List<List<Integer>> solutions,
      int[] num,
      int remain,
      List<Integer> chosenIndices,
      int index) {
    if (remain == 0) {
      List<Integer> solution = new ArrayList<>();
      for (int chosenIndex : chosenIndices) {
        solution.add(num[chosenIndex]);
      }
      solutions.add(solution);
      return;
    }
    if (index == num.length || remain < num[index]) {
      return;
    }
    search(solutions, num, remain, chosenIndices, index + 1);
    if (!(index > 0
        && num[index] == num[index - 1]
        && (chosenIndices.isEmpty() || chosenIndices.get(chosenIndices.size() - 1) != index - 1))) {
      chosenIndices.add(index);
      search(solutions, num, remain - num[index], chosenIndices, index + 1);
      chosenIndices.remove(chosenIndices.size() - 1);
    }
  }
}
