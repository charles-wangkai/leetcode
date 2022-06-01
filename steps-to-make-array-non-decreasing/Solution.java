import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int totalSteps(int[] nums) {
    int[] prevIndices = IntStream.range(0, nums.length).map(i -> (i == 0) ? -1 : (i - 1)).toArray();
    int[] nextIndices =
        IntStream.range(0, nums.length).map(i -> (i == nums.length - 1) ? -1 : (i + 1)).toArray();
    Set<Integer> removedIndices =
        IntStream.range(1, nums.length)
            .filter(i -> nums[i] < nums[i - 1])
            .boxed()
            .collect(Collectors.toSet());
    int result = 0;
    while (!removedIndices.isEmpty()) {
      Set<Integer> nextRemovedIndices = new HashSet<>();
      for (int removedIndex : removedIndices) {
        int prevIndex = prevIndices[removedIndex];
        int nextIndex = nextIndices[removedIndex];

        if (prevIndex != -1) {
          nextIndices[prevIndex] = nextIndex;
        }
        if (nextIndex != -1) {
          prevIndices[nextIndex] = prevIndex;
        }

        if (prevIndex != -1
            && nextIndex != -1
            && !removedIndices.contains(prevIndex)
            && !removedIndices.contains(nextIndex)
            && nums[nextIndex] < nums[prevIndex]) {
          nextRemovedIndices.add(nextIndex);
        }
      }

      removedIndices = nextRemovedIndices;
      ++result;
    }

    return result;
  }
}