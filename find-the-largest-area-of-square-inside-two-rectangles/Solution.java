class Solution {
  public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
    int n = bottomLeft.length;

    int maxSize = 0;
    for (int i = 0; i < n; ++i) {
      for (int j = i + 1; j < n; ++j) {
        maxSize =
            Math.max(
                maxSize,
                Math.min(
                    Math.max(
                        0,
                        Math.min(topRight[i][0], topRight[j][0])
                            - Math.max(bottomLeft[i][0], bottomLeft[j][0])),
                    Math.max(
                        0,
                        Math.min(topRight[i][1], topRight[j][1])
                            - Math.max(bottomLeft[i][1], bottomLeft[j][1]))));
      }
    }

    return (long) maxSize * maxSize;
  }
}