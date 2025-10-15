import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public List<Integer> cheapestJump(int[] coins, int maxJump) {
    int[] minCosts = new int[coins.length];
    Arrays.fill(minCosts, Integer.MAX_VALUE);

    for (int i = minCosts.length - 1; i >= 0; --i) {
      if (coins[i] != -1) {
        if (i == minCosts.length - 1) {
          minCosts[i] = coins[i];
        } else {
          for (int j = i + 1; j <= i + maxJump && j < minCosts.length; ++j) {
            if (minCosts[j] != Integer.MAX_VALUE) {
              minCosts[i] = Math.min(minCosts[i], coins[i] + minCosts[j]);
            }
          }
        }
      }
    }

    if (minCosts[0] == Integer.MAX_VALUE) {
      return List.of();
    }

    List<Integer> result = new ArrayList<>();
    result.add(1);
    int index = 0;
    while (index != minCosts.length - 1) {
      for (int next = index + 1; ; ++next) {
        if (minCosts[next] != Integer.MAX_VALUE
            && coins[index] + minCosts[next] == minCosts[index]) {
          index = next;
          result.add(index + 1);

          break;
        }
      }
    }

    return result;
  }
}
