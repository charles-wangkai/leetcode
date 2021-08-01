import java.util.Arrays;

class Solution {
  public long numberOfWeeks(int[] milestones) {
    long sum = Arrays.stream(milestones).asLongStream().sum();
    int max = Arrays.stream(milestones).max().getAsInt();
    long other = sum - max;

    return sum - Math.max(0, max - (other + 1));
  }
}
