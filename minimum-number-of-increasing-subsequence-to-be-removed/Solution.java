import java.util.ArrayList;
import java.util.List;

class Solution {
  public int minOperations(int[] nums) {
    List<Integer> sequence = new ArrayList<>();
    for (int num : nums) {
      int index = findFirstLessIndex(sequence, num);
      if (index == sequence.size()) {
        sequence.add(num);
      } else {
        sequence.set(index, num);
      }
    }

    return sequence.size();
  }

  int findFirstLessIndex(List<Integer> sequence, int target) {
    int result = sequence.size();
    int lower = 0;
    int upper = sequence.size() - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (sequence.get(middle) < target) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }
}