import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Solution {
  public int[] resultArray(int[] nums) {
    Map<Integer, Integer> valueToCompressed = buildValueToCompressed(nums);

    Array array1 = new Array();
    array1.append(nums[0], valueToCompressed.get(nums[0]));

    Array array2 = new Array();
    array2.append(nums[1], valueToCompressed.get(nums[1]));

    for (int i = 2; i < nums.length; ++i) {
      int compressed = valueToCompressed.get(nums[i]);

      int greaterNum1 = array1.computeGreaterNum(compressed);
      int greaterNum2 = array2.computeGreaterNum(compressed);
      ((greaterNum1 > greaterNum2
                  || (greaterNum1 == greaterNum2 && array1.values.size() <= array2.values.size()))
              ? array1
              : array2)
          .append(nums[i], compressed);
    }

    return Stream.concat(array1.values.stream(), array2.values.stream())
        .mapToInt(Integer::intValue)
        .toArray();
  }

  Map<Integer, Integer> buildValueToCompressed(int[] nums) {
    int[] sorted = Arrays.stream(nums).sorted().distinct().toArray();

    return IntStream.range(0, sorted.length)
        .boxed()
        .collect(Collectors.toMap(i -> sorted[i], i -> i + 1));
  }
}

class Array {
  static final int LIMIT = 100000;

  List<Integer> values = new ArrayList<>();
  int[] binaryIndexedTree = new int[Integer.highestOneBit(LIMIT) * 2 + 1];

  int computeGreaterNum(int x) {
    return values.size() - query(binaryIndexedTree, x);
  }

  void append(int value, int compressed) {
    values.add(value);
    update(binaryIndexedTree, compressed, 1);
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