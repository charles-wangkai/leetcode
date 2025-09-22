import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution {
  public int minSplitMerge(int[] nums1, int[] nums2) {
    Map<List<Integer>, Integer> stateToDistance = new HashMap<>();
    stateToDistance.put(Arrays.stream(nums1).boxed().toList(), 0);

    Queue<List<Integer>> queue = new ArrayDeque<>();
    queue.offer(Arrays.stream(nums1).boxed().toList());

    while (true) {
      List<Integer> head = queue.poll();
      if (head.equals(Arrays.stream(nums2).boxed().toList())) {
        return stateToDistance.get(head);
      }

      for (int beginIndex = 0; beginIndex < head.size(); ++beginIndex) {
        for (int middleIndex = beginIndex + 1; middleIndex < head.size(); ++middleIndex) {
          for (int endIndex = middleIndex; endIndex < head.size(); ++endIndex) {
            List<Integer> next = new ArrayList<>(head);
            reverse(next, beginIndex, endIndex);
            reverse(next, beginIndex, middleIndex - 1);
            reverse(next, middleIndex, endIndex);

            if (!stateToDistance.containsKey(next)) {
              stateToDistance.put(next, stateToDistance.get(head) + 1);
              queue.offer(next);
            }
          }
        }
      }
    }
  }

  void reverse(List<Integer> a, int leftIndex, int rightIndex) {
    for (int i = leftIndex, j = rightIndex; i < j; ++i, --j) {
      int temp = a.get(i);
      a.set(i, a.get(j));
      a.set(j, temp);
    }
  }
}