import java.util.Arrays;

class Solution {
  public double average(int[] salary) {
    return Arrays.stream(salary).sorted().limit(salary.length - 1).skip(1).average().getAsDouble();
  }
}
