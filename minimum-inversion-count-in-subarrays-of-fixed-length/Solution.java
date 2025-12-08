import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public long minInversionCount(int[] nums, int k) {
    int[] sortedValues = Arrays.stream(nums).distinct().sorted().toArray();
    Map<Integer, Integer> valueToCompressed =
        IntStream.range(0, sortedValues.length)
            .boxed()
            .collect(Collectors.toMap(i -> sortedValues[i], i -> i + 1));

    int[] binaryIndexedTree = new int[Integer.highestOneBit(sortedValues.length) * 2 + 1];
    long inversionNum = 0;
    for (int i = 0; i < k - 1; ++i) {
      inversionNum += i - query(binaryIndexedTree, valueToCompressed.get(nums[i]));
      update(binaryIndexedTree, valueToCompressed.get(nums[i]), 1);
    }

    long result = Long.MAX_VALUE;
    for (int i = k - 1; i < nums.length; ++i) {
      inversionNum += (k - 1) - query(binaryIndexedTree, valueToCompressed.get(nums[i]));
      update(binaryIndexedTree, valueToCompressed.get(nums[i]), 1);

      result = Math.min(result, inversionNum);

      update(binaryIndexedTree, valueToCompressed.get(nums[i - k + 1]), -1);
      inversionNum -= query(binaryIndexedTree, valueToCompressed.get(nums[i - k + 1]) - 1);
    }

    return result;
  }

  int query(int[] binaryIndexedTree, int index) {
    int result = 0;
    while (index != 0) {
      result += binaryIndexedTree[index];
      index -= index & -index;
    }

    return result;
  }

  void update(int[] binaryIndexedTree, int index, int delta) {
    while (index < binaryIndexedTree.length) {
      binaryIndexedTree[index] += delta;
      index += index & -index;
    }
  }
}