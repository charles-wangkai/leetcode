import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int findMinimumTime(List<Integer> strength, int K) {
    return search(strength, K, IntStream.range(0, strength.size()).toArray(), 0);
  }

  int search(List<Integer> strength, int K, int[] indices, int depth) {
    if (depth == indices.length) {
      return computeTime(strength, K, indices);
    }

    int result = Integer.MAX_VALUE;
    for (int i = depth; i < indices.length; ++i) {
      swap(indices, i, depth);
      result = Math.min(result, search(strength, K, indices, depth + 1));
      swap(indices, i, depth);
    }

    return result;
  }

  void swap(int[] a, int index1, int index2) {
    int temp = a[index1];
    a[index1] = a[index2];
    a[index2] = temp;
  }

  int computeTime(List<Integer> strength, int K, int[] indices) {
    int result = 0;
    int x = 1;
    for (int index : indices) {
      result += (strength.get(index) + x - 1) / x;
      x += K;
    }

    return result;
  }
}