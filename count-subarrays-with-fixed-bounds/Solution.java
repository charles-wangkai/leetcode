import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
  public long countSubarrays(int[] nums, int minK, int maxK) {
    Queue<Integer> minIndices = new ArrayDeque<>();
    for (int i = 0; i < nums.length; ++i) {
      if (nums[i] == minK) {
        minIndices.offer(i);
      }
    }

    Queue<Integer> maxIndices = new ArrayDeque<>();
    for (int i = 0; i < nums.length; ++i) {
      if (nums[i] == maxK) {
        maxIndices.offer(i);
      }
    }

    long result = 0;
    int beginIndex = -1;
    for (int endIndex = 0; endIndex <= nums.length; ++endIndex) {
      if (endIndex == nums.length || nums[endIndex] < minK || nums[endIndex] > maxK) {
        while (!minIndices.isEmpty()
            && minIndices.peek() < endIndex
            && !maxIndices.isEmpty()
            && maxIndices.peek() < endIndex) {
          if (minIndices.peek() <= maxIndices.peek()) {
            int firstMinIndices = minIndices.poll();
            if (firstMinIndices > beginIndex) {
              result += (long) (firstMinIndices - beginIndex) * (endIndex - maxIndices.peek());

              beginIndex = firstMinIndices;
            }
          } else {
            int firstMaxIndices = maxIndices.poll();
            if (firstMaxIndices > beginIndex) {
              result += (long) (firstMaxIndices - beginIndex) * (endIndex - minIndices.peek());

              beginIndex = firstMaxIndices;
            }
          }
        }

        beginIndex = endIndex;
      }
    }

    return result;
  }
}
