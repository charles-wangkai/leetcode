import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
  public double[] medianSlidingWindow(int[] nums, int k) {
    double[] result = new double[nums.length - k + 1];

    PriorityQueue<Integer> lowers = new PriorityQueue<>(Comparator.reverseOrder());
    PriorityQueue<Integer> uppers = new PriorityQueue<>();

    for (int i = 0; i < k - 1; i++) {
      add(lowers, uppers, nums[i]);
    }

    for (int i = 0, beginIndex = 0, endIndex = k - 1;
        endIndex < nums.length;
        i++, beginIndex++, endIndex++) {
      add(lowers, uppers, nums[endIndex]);

      if (lowers.size() == uppers.size()) {
        result[i] = ((double) lowers.peek() + uppers.peek()) / 2;
      } else if (lowers.size() > uppers.size()) {
        result[i] = lowers.peek();
      } else {
        result[i] = uppers.peek();
      }

      remove(lowers, uppers, nums[beginIndex]);
    }

    return result;
  }

  void add(PriorityQueue<Integer> lowers, PriorityQueue<Integer> uppers, int num) {
    if (uppers.isEmpty() || num <= uppers.peek()) {
      lowers.offer(num);
    } else {
      uppers.offer(num);
    }

    if (lowers.size() >= uppers.size() + 2) {
      uppers.offer(lowers.poll());
    } else if (uppers.size() >= lowers.size() + 2) {
      lowers.offer(uppers.poll());
    }
  }

  void remove(PriorityQueue<Integer> lowers, PriorityQueue<Integer> uppers, int num) {
    if (!lowers.isEmpty() && num <= lowers.peek()) {
      lowers.remove(num);
    } else {
      uppers.remove(num);
    }
  }
}
