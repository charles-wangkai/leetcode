import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
  public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
    PriorityQueue<Pair> pq =
        new PriorityQueue<>(
            Comparator.comparing(pair -> nums1[pair.index1()] + nums2[pair.index2()]));
    pq.offer(new Pair(0, 0));

    List<List<Integer>> result = new ArrayList<>();
    for (int i = 0; i < k && !pq.isEmpty(); ++i) {
      Pair head = pq.poll();
      result.add(List.of(nums1[head.index1()], nums2[head.index2()]));

      if (head.index2() != nums2.length - 1) {
        pq.offer(new Pair(head.index1(), head.index2() + 1));
      }
      if (head.index2() == 0 && head.index1() != nums1.length - 1) {
        pq.offer(new Pair(head.index1() + 1, 0));
      }
    }

    return result;
  }
}

record Pair(int index1, int index2) {}
