import java.util.Arrays;

class Solution {
  public boolean checkGoodInteger(int n) {
    int[] digits = String.valueOf(n).chars().map(c -> c - '0').toArray();

    return Arrays.stream(digits).map(digit -> digit * digit).sum() - Arrays.stream(digits).sum()
        >= 50;
  }
}