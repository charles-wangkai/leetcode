import java.util.stream.IntStream;

class Solution {
  public int countArrangement(int N) {
    return search(IntStream.rangeClosed(1, N).toArray(), 0);
  }

  int search(int[] arrangement, int index) {
    if (index == arrangement.length) {
      return 1;
    }

    int result = 0;
    for (int i = index; i < arrangement.length; ++i) {
      if (arrangement[i] % (index + 1) == 0 || (index + 1) % arrangement[i] == 0) {
        swap(arrangement, index, i);
        result += search(arrangement, index + 1);
        swap(arrangement, index, i);
      }
    }

    return result;
  }

  void swap(int[] a, int index1, int index2) {
    int temp = a[index1];
    a[index1] = a[index2];
    a[index2] = temp;
  }
}
