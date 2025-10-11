import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public int minThreshold(int[] nums, int k) {
    int result = -1;
    int lower = 1;
    int upper = Arrays.stream(nums).max().getAsInt() - Arrays.stream(nums).min().getAsInt();
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(nums, k, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int[] nums, int k, int threshold) {
    SortedMap<Integer, List<Integer>> valueToIndices = new TreeMap<>(Comparator.reverseOrder());
    for (int i = 0; i < nums.length; ++i) {
      valueToIndices.putIfAbsent(nums[i], new ArrayList<>());
      valueToIndices.get(nums[i]).add(i);
    }

    int pairNum = 0;
    Queue<Integer> queue = new ArrayDeque<>();
    int[] binaryIndexedTree = new int[Integer.highestOneBit(nums.length) * 2 + 1];
    for (int value : valueToIndices.keySet()) {
      while (!queue.isEmpty() && queue.peek() - value > threshold) {
        for (int index : valueToIndices.get(queue.poll())) {
          update(binaryIndexedTree, index + 1, -1);
        }
      }

      for (int index : valueToIndices.get(value)) {
        pairNum += query(binaryIndexedTree, index + 1);
      }

      for (int index : valueToIndices.get(value)) {
        update(binaryIndexedTree, index + 1, 1);
      }
      queue.offer(value);
    }

    return pairNum >= k;
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