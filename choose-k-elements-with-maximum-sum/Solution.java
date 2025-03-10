import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public long[] findMaxSum(int[] nums1, int[] nums2, int k) {
    SortedMap<Integer, List<Integer>> valueToIndices = new TreeMap<>();
    for (int i = 0; i < nums1.length; ++i) {
      valueToIndices.putIfAbsent(nums1[i], new ArrayList<>());
      valueToIndices.get(nums1[i]).add(i);
    }

    long[] result = new long[nums1.length];
    PriorityQueue<Integer> chosen = new PriorityQueue<>();
    long chosenSum = 0;
    for (List<Integer> indices : valueToIndices.values()) {
      for (int index : indices) {
        result[index] = chosenSum;
      }

      for (int index : indices) {
        chosen.offer(nums2[index]);
        chosenSum += nums2[index];

        if (chosen.size() == k + 1) {
          chosenSum -= chosen.poll();
        }
      }
    }

    return result;
  }
}