import java.util.Arrays;
import java.util.PriorityQueue;

class KthLargest {
  private PriorityQueue<Integer> pq = new PriorityQueue<>();
  private int k;

  public KthLargest(int k, int[] nums) {
    this.k = k;

    Arrays.stream(nums).forEach(this::add);
  }

  public int add(int val) {
    pq.offer(val);
    if (pq.size() == k + 1) {
      pq.poll();
    }

    return pq.peek();
  }
}

// Your KthLargest object will be instantiated and called as such:
// KthLargest obj = new KthLargest(k, nums);
// int param_1 = obj.add(val);
