// https://leetcode.com/problems/frog-jump-ii/solutions/2897888/it-s-greedy-even-and-odd/

import java.util.stream.IntStream;

class Solution {
  public int maxJump(int[] stones) {
    return IntStream.range(0, stones.length - 2)
        .map(i -> stones[i + 2] - stones[i])
        .max()
        .orElse(stones[1] - stones[0]);
  }
}
