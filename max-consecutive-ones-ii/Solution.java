import java.util.ArrayList;
import java.util.List;

public class Solution {
  public int findMaxConsecutiveOnes(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }

    int maxOneNum = 1;

    List<NumberAndCount> ncs = new ArrayList<>();
    for (int num : nums) {
      if (ncs.isEmpty() || num != ncs.get(0).number) {
        ncs.add(0, new NumberAndCount(num, 1));
      } else {
        ncs.get(0).count++;
      }

      if (ncs.size() > 3) {
        ncs.remove(ncs.size() - 1);
      }

      if (ncs.get(0).number == 1) {
        maxOneNum = Math.max(maxOneNum, ncs.get(0).count);

        if (ncs.size() > 1) {
          maxOneNum = Math.max(maxOneNum, ncs.get(0).count + 1);
        }

        if (ncs.size() > 2 && ncs.get(1).count == 1) {
          maxOneNum = Math.max(maxOneNum, ncs.get(0).count + 1 + ncs.get(2).count);
        }
      } else {
        if (ncs.size() > 1) {
          maxOneNum = Math.max(maxOneNum, ncs.get(1).count + 1);
        }
      }
    }

    return maxOneNum;
  }
}

class NumberAndCount {
  int number;
  int count;

  NumberAndCount(int number, int count) {
    this.number = number;
    this.count = count;
  }
}
