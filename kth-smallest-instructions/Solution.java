class Solution {
  public String kthSmallestPath(int[] destination, int k) {
    StringBuilder result = new StringBuilder();
    int hCount = destination[1];
    int vCount = destination[0];
    int remain = k;
    while (hCount + vCount != 0) {
      if (hCount != 0 && C(hCount + vCount - 1, vCount) >= remain) {
        result.append('H');
        --hCount;
      } else {
        if (hCount != 0) {
          remain -= C(hCount + vCount - 1, vCount);
        }

        result.append('V');
        --vCount;
      }
    }

    return result.toString();
  }

  int C(int n, int r) {
    int result = 1;
    for (int i = 0; i < r; ++i) {
      result = (int) ((long) result * (n - i) / (i + 1));
    }

    return result;
  }
}
