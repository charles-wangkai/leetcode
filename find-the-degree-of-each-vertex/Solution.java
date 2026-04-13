import java.util.Arrays;

class Solution {
  public int[] findDegrees(int[][] matrix) {
    return Arrays.stream(matrix).mapToInt(line -> Arrays.stream(line).sum()).toArray();
  }
}