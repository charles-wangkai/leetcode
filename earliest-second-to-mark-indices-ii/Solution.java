// https://leetcode.com/problems/earliest-second-to-mark-indices-ii/solutions/4778732/python3-binary-search-heap/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
  public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
    int result = -1;
    int lower = 1;
    int upper = changeIndices.length;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(nums, changeIndices, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int[] nums, int[] changeIndices, int time) {
    Map<Integer, Integer> posToFirstIndex = new HashMap<>();
    for (int i = 0; i < time; ++i) {
      if (nums[changeIndices[i] - 1] != 0 && !posToFirstIndex.containsKey(changeIndices[i])) {
        posToFirstIndex.put(changeIndices[i], i);
      }
    }

    boolean[] firsts = new boolean[time];
    for (int firstIndex : posToFirstIndex.values()) {
      firsts[firstIndex] = true;
    }

    int count = 0;
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int i = time - 1; i >= 0; --i) {
      if (firsts[i]) {
        pq.offer(nums[changeIndices[i] - 1]);

        if (count == 0) {
          ++count;
          pq.poll();
        } else {
          --count;
        }
      } else {
        ++count;
      }
    }

    return Arrays.stream(nums).asLongStream().sum()
            - pq.stream().mapToInt(Integer::intValue).asLongStream().sum()
            + nums.length
            - pq.size()
        <= count;
  }
}