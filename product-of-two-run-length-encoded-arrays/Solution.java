import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
    List<List<Integer>> result = new ArrayList<>();
    int index1 = 0;
    int index2 = 0;
    while (index1 != encoded1.length) {
      int product = encoded1[index1][0] * encoded2[index2][0];
      int freq = Math.min(encoded1[index1][1], encoded2[index2][1]);

      encoded1[index1][1] -= freq;
      if (encoded1[index1][1] == 0) {
        ++index1;
      }

      encoded2[index2][1] -= freq;
      if (encoded2[index2][1] == 0) {
        ++index2;
      }

      if (result.isEmpty() || product != result.get(result.size() - 1).get(0)) {
        result.add(Arrays.asList(product, freq));
      } else {
        List<Integer> segment = result.get(result.size() - 1);
        segment.set(1, segment.get(1) + freq);
      }
    }

    return result;
  }
}