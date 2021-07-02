import java.util.LinkedList;
import java.util.List;

class Solution {
  public List<Integer> findClosestElements(int[] arr, int k, int x) {
    int leftIndex = findLeftIndex(arr, x);
    int rightIndex = leftIndex + 1;

    LinkedList<Integer> result = new LinkedList<>();
    for (int i = 0; i < k; ++i) {
      if (rightIndex == arr.length
          || (leftIndex != -1 && x - arr[leftIndex] <= arr[rightIndex] - x)) {
        result.addFirst(arr[leftIndex]);
        --leftIndex;
      } else {
        result.addLast(arr[rightIndex]);
        ++rightIndex;
      }
    }

    return result;
  }

  int findLeftIndex(int[] arr, int x) {
    int result = -1;
    int lower = 0;
    int upper = arr.length - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (arr[middle] <= x) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }
}
