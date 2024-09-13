import java.util.Arrays;

class Solution {
  public int[] xorQueries(int[] arr, int[][] queries) {
    int[] prefixXors = new int[arr.length + 1];
    for (int i = 1; i < prefixXors.length; ++i) {
      prefixXors[i] = prefixXors[i - 1] ^ arr[i - 1];
    }

    return Arrays.stream(queries)
        .mapToInt(query -> prefixXors[query[1] + 1] ^ prefixXors[query[0]])
        .toArray();
  }
}
