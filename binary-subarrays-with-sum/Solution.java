import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int numSubarraysWithSum(int[] nums, int goal) {
    List<Integer> zeroNums = new ArrayList<>();
    int zeroNum = 0;
    for (int num : nums) {
      if (num == 0) {
        ++zeroNum;
      } else {
        zeroNums.add(zeroNum);
        zeroNum = 0;
      }
    }
    zeroNums.add(zeroNum);

    return (goal == 0)
        ? zeroNums.stream().mapToInt(x -> x * (x + 1) / 2).sum()
        : IntStream.range(0, zeroNums.size() - goal)
            .map(i -> (zeroNums.get(i) + 1) * (zeroNums.get(i + goal) + 1))
            .sum();
  }
}
