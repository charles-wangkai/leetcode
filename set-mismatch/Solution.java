import java.util.stream.IntStream;

class Solution {
  public int[] findErrorNums(int[] nums) {
    int[] counts = new int[nums.length + 1];
    for (int num : nums) {
      ++counts[num];
    }

    return new int[] {
      IntStream.rangeClosed(1, nums.length).filter(i -> counts[i] == 2).findAny().getAsInt(),
      IntStream.rangeClosed(1, nums.length).filter(i -> counts[i] == 0).findAny().getAsInt()
    };
  }
}
