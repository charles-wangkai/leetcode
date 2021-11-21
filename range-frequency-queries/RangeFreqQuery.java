import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RangeFreqQuery {
  Map<Integer, List<Integer>> valueToIndices = new HashMap<>();

  public RangeFreqQuery(int[] arr) {
    for (int i = 0; i < arr.length; ++i) {
      if (!valueToIndices.containsKey(arr[i])) {
        valueToIndices.put(arr[i], new ArrayList<>());
      }
      valueToIndices.get(arr[i]).add(i);
    }
  }

  public int query(int left, int right, int value) {
    return findRightIndex(valueToIndices.getOrDefault(value, List.of()), right)
        - findLeftIndex(valueToIndices.getOrDefault(value, List.of()), left)
        + 1;
  }

  int findRightIndex(List<Integer> indices, int right) {
    int result = -1;
    int lower = 0;
    int upper = indices.size() - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (indices.get(middle) <= right) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  int findLeftIndex(List<Integer> indices, int left) {
    int result = indices.size();
    int lower = 0;
    int upper = indices.size() - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (indices.get(middle) >= left) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }
}

// Your RangeFreqQuery object will be instantiated and called as such:
// RangeFreqQuery obj = new RangeFreqQuery(arr);
// int param_1 = obj.query(left,right,value);
