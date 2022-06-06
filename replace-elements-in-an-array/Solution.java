import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int[] arrayChange(int[] nums, int[][] operations) {
    Map<Integer, Integer> valueToIndex =
        IntStream.range(0, nums.length).boxed().collect(Collectors.toMap(i -> nums[i], i -> i));

    for (int[] operation : operations) {
      valueToIndex.put(operation[1], valueToIndex.remove(operation[0]));
    }

    int[] result = new int[nums.length];
    for (int value : valueToIndex.keySet()) {
      result[valueToIndex.get(value)] = value;
    }

    return result;
  }
}