import java.util.Arrays;

class Solution {
  public int[] rearrangeArray(int[] nums) {
    int[] sorted = Arrays.stream(nums).boxed().sorted().mapToInt(x -> x).toArray();

    int[] result = new int[nums.length];
    int index = 0;
    for (int value : sorted) {
      if (index >= result.length) {
        index = 1;
      }
      result[index] = value;
      index += 2;
    }

    return result;
  }
}
