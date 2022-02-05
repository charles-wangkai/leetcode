class Solution {
  public int minimumSum(int num) {
    int[] digits = String.valueOf(num).chars().map(c -> c - '0').sorted().toArray();

    return digits[0] * 10 + digits[1] * 10 + digits[2] + digits[3];
  }
}