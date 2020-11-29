import java.util.PriorityQueue;

class Solution {
  public int[] mostCompetitive(int[] nums, int k) {
    PriorityQueue<Integer> pq =
        new PriorityQueue<>(
            (i1, i2) ->
                (nums[i1] != nums[i2])
                    ? Integer.compare(nums[i1], nums[i2])
                    : Integer.compare(i1, i2));
    for (int i = 0; i <= nums.length - k; ++i) {
      pq.offer(i);
    }

    int prevIndex = -1;
    int[] result = new int[k];
    for (int i = 0; i < result.length; ++i) {
      while (true) {
        int index = pq.poll();
        if (index > prevIndex) {
          result[i] = nums[index];
          prevIndex = index;

          if (i != result.length - 1) {
            pq.offer(nums.length - k + i + 1);
          }

          break;
        }
      }
    }

    return result;
  }
}
