import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.stream.IntStream;

class Solution {
  public int validSubarraySize(int[] nums, int threshold) {
    NavigableSet<Integer> restIndices = new TreeSet<>();
    for (int i = -1; i <= nums.length; ++i) {
      restIndices.add(i);
    }

    int[] sortedIndices =
        IntStream.range(0, nums.length)
            .boxed()
            .sorted(Comparator.comparing((Integer i) -> nums[i]).reversed())
            .mapToInt(x -> x)
            .toArray();
    int index = 0;
    for (int size = 1; size <= nums.length; ++size) {
      int minValue = threshold / size + 1;
      while (index != sortedIndices.length && nums[sortedIndices[index]] >= minValue) {
        restIndices.remove(sortedIndices[index]);

        if (restIndices.higher(sortedIndices[index]) - restIndices.lower(sortedIndices[index]) - 1
            >= size) {
          return size;
        }

        ++index;
      }
    }

    return -1;
  }
}