import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
  static final double EPSILON = 1e-9;

  public int halveArray(int[] nums) {
    PriorityQueue<Double> pq = new PriorityQueue<>(Comparator.reverseOrder());
    for (int num : nums) {
      pq.offer((double) num);
    }

    double current = Arrays.stream(nums).asDoubleStream().sum();
    double target = current / 2;

    int result = 0;
    while (current - target > EPSILON) {
      double head = pq.poll();
      current -= head / 2;
      ++result;

      pq.offer(head / 2);
    }

    return result;
  }
}