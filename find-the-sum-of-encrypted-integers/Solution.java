import java.util.Arrays;

class Solution {
  public int sumOfEncryptedInt(int[] nums) {
    return Arrays.stream(nums)
        .map(
            x ->
                Integer.parseInt("1".repeat(String.valueOf(x).length()))
                    * (String.valueOf(x).chars().max().getAsInt() - '0'))
        .sum();
  }
}