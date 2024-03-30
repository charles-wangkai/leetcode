import java.util.Arrays;

class Solution {
  public int minimumLevels(int[] possible) {
    int leftSum = 0;
    int rightSum = Arrays.stream(possible).map(x -> (x == 1) ? 1 : -1).sum();
    for (int i = 0; i < possible.length - 1; ++i) {
      int current = (possible[i] == 1) ? 1 : -1;
      leftSum += current;
      rightSum -= current;
      if (leftSum > rightSum) {
        return i + 1;
      }
    }

    return -1;
  }
}