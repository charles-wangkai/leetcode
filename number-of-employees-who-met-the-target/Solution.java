import java.util.Arrays;

class Solution {
  public int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
    return (int) Arrays.stream(hours).filter(x -> x >= target).count();
  }
}
