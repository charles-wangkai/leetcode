import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
  public int maximumProduct(int[] nums) {
    int result = Integer.MIN_VALUE;

    List<Integer> negatives = new ArrayList<>();
    List<Integer> positives = new ArrayList<>();
    for (int num : nums) {
      if (num == 0) {
        result = Math.max(result, 0);
      } else if (num < 0) {
        negatives.add(num);
      } else {
        positives.add(num);
      }
    }

    Collections.sort(negatives);
    Collections.sort(positives);

    if (positives.size() >= 3) {
      result =
          Math.max(
              result,
              positives.get(positives.size() - 3)
                  * positives.get(positives.size() - 2)
                  * positives.get(positives.size() - 1));
    }
    if (!negatives.isEmpty() && positives.size() >= 2) {
      result =
          Math.max(
              result, negatives.get(negatives.size() - 1) * positives.get(0) * positives.get(1));
    }
    if (negatives.size() >= 2 && !positives.isEmpty()) {
      result =
          Math.max(
              result, negatives.get(0) * negatives.get(1) * positives.get(positives.size() - 1));
    }
    if (negatives.size() >= 3) {
      result =
          Math.max(
              result,
              negatives.get(negatives.size() - 3)
                  * negatives.get(negatives.size() - 2)
                  * negatives.get(negatives.size() - 1));
    }

    return result;
  }
}
