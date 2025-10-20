import java.util.Arrays;

class Solution {
  public int finalValueAfterOperations(String[] operations) {
    return Arrays.stream(operations)
        .mapToInt(operation -> (operation.charAt(1) == '+') ? 1 : -1)
        .sum();
  }
}
