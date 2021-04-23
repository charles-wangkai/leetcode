import java.util.Arrays;

class Solution {
  public int getXORSum(int[] arr1, int[] arr2) {
    return Arrays.stream(arr1).reduce((x, y) -> x ^ y).getAsInt()
        & Arrays.stream(arr2).reduce((x, y) -> x ^ y).getAsInt();
  }
}
