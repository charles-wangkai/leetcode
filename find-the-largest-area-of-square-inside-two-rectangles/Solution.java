class Solution {
  public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
    int n = bottomLeft.length;

    long result = 0;
    for (int i = 0; i < n; ++i) {
      for (int j = i + 1; j < n; ++j) {
        int size =
            Math.min(
                Math.max(
                    0,
                    Math.min(topRight[i][0], topRight[j][0])
                        - Math.max(bottomLeft[i][0], bottomLeft[j][0])),
                Math.max(
                    0,
                    Math.min(topRight[i][1], topRight[j][1])
                        - Math.max(bottomLeft[i][1], bottomLeft[j][1])));

        result = Math.max(result, (long) size * size);
      }
    }

    return result;
  }
}