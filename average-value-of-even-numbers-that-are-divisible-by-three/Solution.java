import java.util.Arrays;

class Solution {
  public int averageValue(int[] nums) {
    int[] elements = Arrays.stream(nums).filter(x -> x % 6 == 0).toArray();

    return (elements.length == 0) ? 0 : (Arrays.stream(elements).sum() / elements.length);
  }
}
