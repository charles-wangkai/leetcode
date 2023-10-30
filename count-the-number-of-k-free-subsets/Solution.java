import java.util.SortedSet;
import java.util.TreeSet;

class Solution {
  public long countTheNumOfKFreeSubsets(int[] nums, int k) {
    SortedSet<Integer> values = new TreeSet<>();
    for (int num : nums) {
      values.add(num);
    }

    long result = 1;
    while (!values.isEmpty()) {
      int size = 0;
      for (int i = values.first(); values.contains(i); i += k) {
        values.remove(i);
        ++size;
      }

      result *= computeSubsetNum(size);
    }

    return result;
  }

  long computeSubsetNum(int size) {
    long prev = 1;
    long curr = 2;
    for (int i = 0; i < size - 1; ++i) {
      long next = prev + curr;

      prev = curr;
      curr = next;
    }

    return curr;
  }
}
