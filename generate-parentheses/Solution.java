import java.util.ArrayList;
import java.util.List;

class Solution {
  public List<String> generateParenthesis(int n) {
    List<String> sequences = new ArrayList<>();
    search(sequences, new char[n + n], 0, n, n);

    return sequences;
  }

  void search(List<String> result, char[] sequence, int index, int leftRest, int rightRest) {
    if (leftRest == 0 && rightRest == 0) {
      result.add(String.valueOf(sequence));

      return;
    }

    if (leftRest > 0) {
      sequence[index] = '(';
      search(result, sequence, index + 1, leftRest - 1, rightRest);
    }
    if (rightRest > leftRest) {
      sequence[index] = ')';
      search(result, sequence, index + 1, leftRest, rightRest - 1);
    }
  }
}
